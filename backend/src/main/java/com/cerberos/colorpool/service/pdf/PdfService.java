package com.cerberos.colorpool.service.pdf;

import com.cerberos.colorpool.advice.exception.CPdfNotCreateException;
import com.cerberos.colorpool.advice.exception.CThemeNotFoundException;
import com.cerberos.colorpool.entity.pdf.Pdf;
import com.cerberos.colorpool.model.pdf.PdfModel;
import com.cerberos.colorpool.repository.pdf.PdfJpaRepository;
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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.charset.Charset;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final PdfJpaRepository pdfJpaRepository;
    private final String pdfFolderPath = "../../colorpoolmd/pdf/";

    @Transactional
    public long savePDF(PdfModel pdfModel){
        long newPDFId = getNewPDFpath(pdfModel);
        System.out.println(pdfModel);
        //pdf 저장
        try {
            createPDF(pdfModel);
            Pdf new_pdf = Pdf.builder()
                    .contents(pdfModel.getContents())
                    .path(pdfModel.getPath())
                    .build();
            pdfJpaRepository.save(new_pdf);
        }catch (Exception e){
            throw new CPdfNotCreateException();
        }
        return newPDFId;
    }

    @Transactional
    public String updatePDF(Pdf pdf){
        String result="";

        return result;
    }

    public String viewMyPDF(){
        String result="";

        return result;
    }


    //pdf를 저장할 새로운 경로를 지정
    public long getNewPDFpath(PdfModel pdfModel) {
        String newPDFpath = "";
        //count 쿼리문 실행
        long nextPDFid = pdfJpaRepository.count() + 1;
        newPDFpath += pdfFolderPath + nextPDFid;
        pdfModel.setPath(newPDFpath);
        return nextPDFid;
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
    public void createPDF(PdfModel pdfModel) throws DocumentException, IOException, CPdfNotCreateException {
        String pdfFilePath = pdfModel.getPath();
        String pdfContents = pdfModel.getContents();

        File saveFolder = new File(pdfFolderPath);
        if(!saveFolder.exists() || saveFolder.isFile()){
            saveFolder.mkdirs();
        }
        pdfFilePath += ".pdf";

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
        CssFile cssFile = XMLWorkerHelper.getCSS(new FileInputStream(pdfFolderPath+"pdf.css"));
        cssResolver.addCss(cssFile);

        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register(pdfFolderPath + "NotoSansKR-Regular.otf", "NotoSansKR");
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
    }
}
