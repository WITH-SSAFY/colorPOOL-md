package com.cerberos.colorpool.service.pdf;

import com.cerberos.colorpool.advice.exception.CContentsNotFoundException;
import com.cerberos.colorpool.advice.exception.CPdfNotCreateException;
import com.cerberos.colorpool.entity.pdf.Pdf;
import com.cerberos.colorpool.model.pdf.PdfModel;
import com.cerberos.colorpool.repository.pdf.PdfJpaRepository;
import com.cerberos.colorpool.s3.S3api;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import lombok.RequiredArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final PdfJpaRepository pdfJpaRepository;
    private final S3api s3api;

    private final String tempPdfFolder = "../../colorpoolmd/pdf/";
    private final String staticFolder = "./src/main/resources/static/";
    private final String pdfFolder = "http://k3a501.p.ssafy.io/resource/pdf/";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getOneContents(int id){
        Pdf pdf = pdfJpaRepository.findById(id).orElseThrow(CContentsNotFoundException::new);

        String contents = pdf.getContents();
        return contents;
    }

    public PdfModel.Res uploadPdfAndMarkdown(PdfModel.Req pdfReq){
        int newPdfId = getNewPdfId();
        String newPdfName = getNewPdfName(newPdfId);
        String newPdfPath = pdfFolder+newPdfName;
        String contents = pdfReq.getContents();
        PdfModel.Res pdfRes = PdfModel.Res.builder()
                                .id(newPdfId)
                                .contents(contents)
                                .path(newPdfPath)
                                .build();
        try {
            //upload pdf to S3
            MultipartFile pdfFile = createPdf(contents, newPdfName);
            s3api.upload(pdfFile,"/pdf",pdfFile.getOriginalFilename());

            Pdf new_pdf = pdfRes.toEntity();
            pdfJpaRepository.save(new_pdf);
            logger.info("a new PDF(id : "+new_pdf.getId()+") is created successfully");
        }catch (Exception e){
            logger.info("failed to create a new PDF");
            throw new CPdfNotCreateException();
        }
        return pdfRes;
    }

    public int getNewPdfId(){
        Optional<Pdf> lastPdf = pdfJpaRepository.findFirstByOrderByIdDesc();
        long newPdfId = 1;
        if(lastPdf.isPresent()){
            newPdfId = lastPdf.get().getId()+1;
        }
        return (int) newPdfId;
    }

    public String getNewPdfName(int newPdfId) {
        String newPdfName = newPdfId+".pdf";
        newPdfName = getHash(newPdfName)+".pdf";
        return newPdfName;
    }

    public String getHash(String newPdfFileName){
        long hash = 17;
        hash = 31 * hash + newPdfFileName.hashCode();
        return Long.toString(hash);
    }

    //html to pdf using itext
    public MultipartFile createPdf(String contents, String newPdfName) throws DocumentException, IOException, CPdfNotCreateException {
        String tempPdfPath = tempPdfFolder+newPdfName;

        String pageCloseTag = "</div>";
        String[] pageContentsArr = contents.split(pageCloseTag);
        ArrayList<String> pageContentsList = new ArrayList<>();
        for(String pageContents : pageContentsArr){
            pageContents += pageCloseTag;
            pageContentsList.add(pageContents);
        }

        File tempSaveFolder = new File(tempPdfFolder);
        if(!tempSaveFolder.exists() || tempSaveFolder.isFile()){
            tempSaveFolder.mkdirs();
        }

        File tempPdf = new File(tempPdfPath);
        if(tempPdf.isFile()){
            tempPdf.delete();
        }

        //pdf init settings
        Rectangle pageSize = new RectangleReadOnly(795,445);
        Document document = new Document(pageSize,0,0,0,0);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(tempPdfPath));
        document.open();

        for(String pageContents : pageContentsList){
            String realContents = "<html><body style='font-family: NanumGothic;'>" + pageContents + "</body></html>";
            XMLWorkerHelper helper = XMLWorkerHelper.getInstance();

            //css
            CSSResolver cssResolver = new StyleAttrCSSResolver();
            CssFile cssFile = helper.getCSS(new FileInputStream(staticFolder+"pdf.css"));
            cssResolver.addCss(cssFile);

            //font
            XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
            fontProvider.register(staticFolder + "NanumGothic-Regular.ttf", "NanumGothic");
            CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

            HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

            PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
            HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
            CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

            XMLWorker worker = new XMLWorker(css, true);
            XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));

            StringReader strReader = new StringReader(realContents);
            xmlParser.parse(strReader);
            document.newPage();
        }
        document.close();
        writer.close();

        // transform pdf to multipartfile
        String contentType = "application/pdf";
        MultipartFile pdfMultipartFile = getMultipartFile(newPdfName,tempPdfPath,contentType);

        //remove tempPdfFile
        if(tempPdf.isFile()){
            tempPdf.delete();
        }

        return pdfMultipartFile;
    }

    public MultipartFile getMultipartFile(String fileName,String filePath, String contentType){
        Path path = Paths.get(filePath);
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (IOException e) {
        }
        MultipartFile multipartFile = new MockMultipartFile(fileName,
                fileName, contentType, content);
        return multipartFile;
    }

}
