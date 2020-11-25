package com.cerberos.colorpool.service.schedule;

import com.cerberos.colorpool.entity.pdf.Pdf;
import com.cerberos.colorpool.repository.pdf.PdfJpaRepository;
import com.cerberos.colorpool.s3.S3api;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final PdfJpaRepository pdfJpaRepository;
    private final S3api s3api;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Scheduled(cron = "0 0 18 * * *")
//    public void clearPdfBucket(){
//        List<Pdf> allPdfList = pdfJpaRepository.findAll();
//
//        if(allPdfList.isEmpty()) return;
//
//        String keyPrefix = "pdf/";
//        String subPath = "/pdf";
//
//        List<String> allPdfNameList = new ArrayList<>();
//        for(Pdf pdf : allPdfList){
//            String[] splitedPath = pdf.getPath().split("[/]");
//            String pdfName = splitedPath[splitedPath.length-1];
//            allPdfNameList.add(keyPrefix+pdfName);
//        }
//        s3api.delete(allPdfNameList,subPath);
//        pdfJpaRepository.deleteAll();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        String time = simpleDateFormat.format(date);
//        logger.info(time+" - all pdfFiles deleted");
//    }
}
