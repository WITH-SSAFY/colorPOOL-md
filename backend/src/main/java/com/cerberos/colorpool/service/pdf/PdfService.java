package com.cerberos.colorpool.service.pdf;

import com.cerberos.colorpool.advice.exception.CImageNotUploadException;
import com.cerberos.colorpool.advice.exception.CPdfNotCreateException;
import com.cerberos.colorpool.advice.exception.CThemeNotFoundException;
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
import javassist.tools.reflect.CannotInvokeException;
import lombok.RequiredArgsConstructor;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.transaction.Transactional;
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
    private final String imageFolderPath = "../../colorpoolmd/image/";
    private final String staticFolder = "./src/main/resources/static/";
    private final String pdfFolder = "http://k3a501.p.ssafy.io/api-pdf/";
    private final String imageFolder = "http://k3a501.p.ssafy.io/api-image/";

    public PdfModel.Res uploadPdfAndMarkdown(PdfModel.Req pdfReq){
        String newPdfName = getNewPdfName();
        String newPdfPath = pdfFolder+newPdfName;
        String contents = pdfReq.getContents();
        PdfModel.Res pdfRes = PdfModel.Res.builder()
                                .contents(contents)
                                .path(newPdfPath)
                                .build();
        try {
            //upload pdf to S3
            MultipartFile pdfFile = createPdf(contents, newPdfName);
            s3api.upload(pdfFile,"/pdf",pdfFile.getOriginalFilename());

            Pdf new_pdf = pdfRes.toEntity();
            pdfJpaRepository.save(new_pdf);
        }catch (Exception e){
            throw new CPdfNotCreateException();
        }
        return pdfRes;
    }

//    public String uploadImage(){
//        String newImageName = "batt.jpg";
//        String newImagePath = imageFolder+newImageName;
//        String type = newImageName.split("[.]")[1];
//
//        //////// this codes will be removed, just for test //////
//        Path path = Paths.get(imageFolderPath+newImageName);
//        String contentType = getContentType(type);
//        byte[] content = null;
//        try {
//            content = Files.readAllBytes(path);
//        } catch (IOException e) {
//        }
//        /////////////////////////////////////////////////////////
//
//
//
//        MultipartFile imageMultipartFile = new MockMultipartFile(newImageName,
//                newImageName, contentType, content);
//        System.out.println("image Content- Type : "+imageMultipartFile.getContentType());
//        try{
//            s3api.upload(imageMultipartFile,"/image");
//        }catch (Exception e){
//            throw new CImageNotUploadException();
//        }
//        return newImagePath;
//    }

    public String getNewPdfName() {
        Optional<Pdf> lastPdf = pdfJpaRepository.findFirstByOrderByIdDesc();
        long newPdfId = 1;
        if(lastPdf.isPresent()){
            newPdfId = lastPdf.get().getId()+1;
        }
        String newPdfName = newPdfId+".pdf";
        newPdfName = getHash(newPdfName)+".pdf";
        return newPdfName;
    }

    private String getContentType(String type) {
        if(type.equals("jpg")){
            type = "jpeg";
        }
        return "application/"+type;
    }

    public String getHash(String newPdfFileName){
        long hash = 17;
        hash = 31 * hash + newPdfFileName.hashCode();
        return Long.toString(hash);
    }

    //html to pdf using itext
    public MultipartFile createPdf(String contents, String newPdfName) throws DocumentException, IOException, CPdfNotCreateException {
        String tempPdfPath = tempPdfFolder+newPdfName;
        //pdfModel.setPath(pdfFolder+newPdfName);
        //String contents = pdfModel.getContents();

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
            String realContents = "<html><body style='family: NanumGothic;'>" + pageContents + "</body></html>";
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
    //    image to pdf
//    public void createPDF(PdfModel pdfModel) throws DocumentException, IOException{
//        String pdfFilePath = pdfModel.getPath();
//        pdfFilePath += ".pdf";
//        File saveFolder = new File(pdfFolderPath);
//        if(!saveFolder.exists() || saveFolder.isFile()){
//            saveFolder.mkdirs();
//        }
//        Rectangle pageSize = new RectangleReadOnly(960,540);//ppt real size
//        Document document = new Document(pageSize, 20, 20, 20, 20);
//        PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
//        document.open();
//        Image image1 = Image.getInstance("C:/Users/multicampus/jaeul/colorpoolmd/pdf/image/1.jpg");
//        Image image2 = Image.getInstance("C:/Users/multicampus/jaeul/colorpoolmd/pdf/image/2.jpg");
//        Image image3 = Image.getInstance("C:/Users/multicampus/jaeul/colorpoolmd/pdf/image/3.png");
//        document.add(image1);
//        document.add(image2);
//        document.add(image3);
//
//        document.close();
//
//        // Closing the document
//        document.close();
//
//        System.out.println("Image added");
//
//    }

//    public String createPdfByWk(){
//        //com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf pdfWrapper =  new com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf();
//        XvfbConfig xc = new XvfbConfig();
//        xc.addParams(new Param("--auto-servernum"), new Param("--server-num=1"));
//        WrapperConfig wc = new WrapperConfig("wkhtmltopdf");
//        com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf pdfWrapper = new com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf(wc);
//
//        pdfWrapper.addPageFromUrl("https://www.naver.com/");
//        String path = "../../colorpoolmd/pdf/output.pdf";
//
//        try {
//            pdfWrapper.saveAs("../../colorpoolmd/pdf/output.pdf");
//        }
//        catch (IOException e) {
//            //throw  new Xhtml2PdfChangeException(this, "Change fail xhtml to pdf. IOException message = " + e.getMessage());
//            e.printStackTrace();
//        }
//        catch (InterruptedException e) {
//            //throw  new Xhtml2PdfChangeException(this, "Change fail xhtml to pdf. InterruptedException message = " + e.getMessage());
//            e.printStackTrace();
//        }
//        return path;
//    }
//    public String createPdfhtmltopdf(String contents){
//        String path = "../../colorpoolmd/pdf/"+getNewPdfFileName()+".pdf";
////        boolean success = HtmlToPdf.create()
////                .object(HtmlToPdfObject.forUrl("https://www.naver.com/"))
////                .convert(path);
//        boolean success = HtmlToPdf.create()
//                .object(HtmlToPdfObject.forHtml(contents))
//                .
//                .convert(path);
//        return path;
//    }
//
//    public String createPdfByopenhtml(String contents){
//        String path = "../../colorpoolmd/pdf/"+getNewPdfFileName()+".pdf";
//        System.out.println("Starting");
//
//        try{
//            PdfRendererBuilder pdfBuilder = new PdfRendererBuilder();
//            float width = 280.5f;
//            float height = 157.0f;
//            pdfBuilder.useDefaultPageSize(width,height, BaseRendererBuilder.PageSizeUnits.MM);
//            String html = "<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"./src/main/resources/static/pdf.css\"/></head><body>";
//            html += contents;
//            html += "</body></html>";
//
//            File file = new File(path);
//            FileOutputStream fop = new FileOutputStream(file);
//
//            W3CDom w3cDom = new W3CDom();
//            org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(Jsoup.parse(html));
//
//            pdfBuilder.withW3cDocument(w3cDoc, "/");
//            pdfBuilder.toStream(fop);
//            pdfBuilder.run();
//            fop.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        return path;
//    }


}
