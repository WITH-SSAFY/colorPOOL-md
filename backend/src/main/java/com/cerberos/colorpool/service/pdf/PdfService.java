package com.cerberos.colorpool.service.pdf;

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
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final PdfJpaRepository pdfJpaRepository;
    private final S3api s3api;
    private final String pdfFolderPath = "../../colorpoolmd/pdf/";
    private final String staticFoldelPath = "./src/main/resources/static/";

    public String savePDF(PdfModel pdfModel){
        String newPdfFileName = getNewPdfFileName(pdfModel);
        String pdfS3FilePath = "https://colorpool-md.s3.ap-northeast-2.amazonaws.com/pdf/"+newPdfFileName;
        //pdf 저장
        try {
            MultipartFile pdfFile = createPdf(pdfModel, newPdfFileName);
            s3api.uploadPdf(pdfFile);
            Pdf new_pdf = Pdf.builder()
                    .contents(pdfModel.getContents())
                    .path(pdfModel.getPath())
                    .build();
            pdfJpaRepository.save(new_pdf);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pdfS3FilePath;
    }

    @Transactional
    public String updatePDF(Pdf pdf){
        String result="";

        return result;
    }

    public String downloadPdf(){
        String result="";

        return result;
    }


    //새로 저장할 pdf의 파일명을 지정
    public String getNewPdfFileName(PdfModel pdfModel) {
        //count 쿼리문 실행
        String nextPdfFileName = Long.toString(pdfJpaRepository.count() + 1)+".pdf";
        return nextPdfFileName;
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
    //html to pdf
    public MultipartFile createPdf(PdfModel pdfModel, String newPdfFileName) throws DocumentException, IOException, CPdfNotCreateException {
        String pdfFilePath = pdfFolderPath+newPdfFileName;
        pdfModel.setPath(pdfFilePath);
        String pdfContents = pdfModel.getContents();


        File saveFolder = new File(pdfFolderPath);
        if(!saveFolder.exists() || saveFolder.isFile()){
            saveFolder.mkdirs();
        }

        //pdf 설정
        Rectangle pageSize = new RectangleReadOnly(960,540);//ppt real size
        Document document = new Document(pageSize,20,20,20,20);

        ///** This is the a5 format */
        //   public static final Rectangle A5 = new RectangleReadOnly(420,595);

        File pdfFile = new File(pdfFilePath);
        if(pdfFile.isFile()){
            pdfFile.delete();
        }

        //pdf를 만드는 작업
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
        document.open();

//        //이미지 태그 절대경로 사용해야 한다.(반드시)
//        //pdfContents = pdfContents.replaceAll("src=\"/", "src=\"http://127.0.0.1:8080/");
//        //폰트를 설정한다. 폰트 설정 누락시 한글이 안보이는 경우 발생
        pdfContents = "<html><body style='font-family: NotoSansKR;'>" + pdfContents + "</body></html>";

        XMLWorkerHelper helper = XMLWorkerHelper.getInstance();

        //css
        CSSResolver cssResolver = new StyleAttrCSSResolver();
        CssFile cssFile = XMLWorkerHelper.getCSS(new FileInputStream(staticFoldelPath+"pdf.css"));
        cssResolver.addCss(cssFile);

        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register(staticFoldelPath + "NotoSansKR-Regular.otf", "NotoSansKR");
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

        // html을 pdf로 변환시작
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

        XMLWorker worker = new XMLWorker(css, true);
        XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));

        StringReader strReader = new StringReader(pdfContents);
        xmlParser.parse(strReader);
        document.close();
        writer.close();

        //egov의 첨부파일 형태로 추가하기 위해서 MultipartFile을 만들어 준다.
        Path path = Paths.get(pdfFilePath);
        String contentType = "application/pdf";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }

        MultipartFile result = new MockMultipartFile(newPdfFileName,
                newPdfFileName, contentType, content);

        //원본파일 삭제
        if(pdfFile.isFile()){
            pdfFile.delete();
        }
        return result;
    }


}
