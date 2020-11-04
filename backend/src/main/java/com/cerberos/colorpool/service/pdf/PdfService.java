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

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.transaction.Transactional;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final PdfJpaRepository pdfJpaRepository;
    private final S3api s3api;
    private final String pdfFolderPath = "../../colorpoolmd/pdf/";
    private final String imageFolderPath = "../../colorpoolmd/image/";

    private final String staticFoldelPath = "./src/main/resources/static/";
    private final String newPdfFileFolder = "http://k3a501.p.ssafy.io/api-pdf/";
    private final String newImageFileFolder = "http://k3a501.p.ssafy.io/api-image/";


    public String getHash(String newPdfFileName){
        long hash = 17;
        hash = 31 * hash + newPdfFileName.hashCode();
        return Long.toString(hash);
    }

    public String savePDF(PdfModel pdfModel){
        String newPdfFileName = getNewPdfFileName()+".pdf";
        String pdfS3FilePath = newPdfFileFolder+newPdfFileName;
        //pdf 저장
        try {
            MultipartFile pdfFile = createPdf(pdfModel, newPdfFileName);
            s3api.upload(pdfFile,"pdf");
            Pdf new_pdf = Pdf.builder()
                    .contents(pdfModel.getContents())
                    .path(pdfModel.getPath())
                    .build();
            pdfJpaRepository.save(new_pdf);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pdfS3FilePath;
//        return createPdfByWk();
//        return createPdfhtmltopdf(pdfModel.getContents());
//        return createPdfByopenhtml(pdfModel.getContents());
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
    public String getNewPdfFileName() {
        //난수 생성 (0~1사이)
        //count 쿼리문 실행
        long newPdfId = pdfJpaRepository.count() + 1;
        String nextPdfFileName = newPdfId+".pdf";
        nextPdfFileName = getHash(nextPdfFileName);
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
    //html to pdf using itext
    public MultipartFile createPdf(PdfModel pdfModel, String newPdfFileName) throws DocumentException, IOException, CPdfNotCreateException {
        String pdfFilePath = pdfFolderPath+newPdfFileName;
        pdfModel.setPath(newPdfFileFolder+newPdfFileName);
        String pdfContents = pdfModel.getContents();
        ///section tag parsing
        String[] sectionArr = pdfContents.split("</section>");
        ArrayList<String> sectionPdfContents = new ArrayList<>();
        for(String section : sectionArr){
            String[] tmp = section.split("<section>");
            sectionPdfContents.add(tmp[1]);
        }

        File saveFolder = new File(pdfFolderPath);
        if(!saveFolder.exists() || saveFolder.isFile()){
            saveFolder.mkdirs();
        }

        //pdf 설정
        Rectangle pageSize = new RectangleReadOnly(795,445);//ppt real size (960,540)
        Document document = new Document(pageSize,80,80,40,40);

        //public static final Rectangle A4 = new RectangleReadOnly(595,842);
        ///** This is the a5 format *
        ///public static final Rectangle A5 = new RectangleReadOnly(420,595);
        File pdfFile = new File(pdfFilePath);
        if(pdfFile.isFile()){
            pdfFile.delete();
        }

        //pdf를 만드는 작업
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
        document.open();

        //이미지 태그 절대경로 사용해야 한다.(반드시)
        //pdfContents = pdfContents.replaceAll("src=\"/", "src=\"http://127.0.0.1:8080/");

        //img src가 local 인 경우
        //s3 저장해야함

        //폰트를 설정한다. 폰트 설정 누락시 한글이 안보이는 경우 발생
        for(String contents : sectionPdfContents){
            String realContents = "<html><body style='font-family: NotoSansKR;'>" + contents + "</body></html>";
            XMLWorkerHelper helper = XMLWorkerHelper.getInstance();

            //System.out.println(realContents);
            //css
            CSSResolver cssResolver = new StyleAttrCSSResolver();
            CssFile cssFile = helper.getCSS(new FileInputStream(staticFoldelPath+"pdf.css"));
            cssResolver.addCss(cssFile);

            //font
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

            StringReader strReader = new StringReader(realContents);
            xmlParser.parse(strReader);
            document.newPage();
        }
        document.close();
        writer.close();

        //첨부파일 형태로 추가하기 위해서 MultipartFile을 만들어 준다.
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
