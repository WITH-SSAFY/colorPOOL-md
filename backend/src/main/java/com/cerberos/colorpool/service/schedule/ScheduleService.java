package com.cerberos.colorpool.service.schedule;

import com.cerberos.colorpool.entity.pdf.Pdf;
import com.cerberos.colorpool.repository.pdf.PdfJpaRepository;
import com.cerberos.colorpool.s3.S3api;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final PdfJpaRepository pdfJpaRepository;
    private final S3api s3api;

    @Scheduled(cron = "0 30 23 * * *")
    public void clearPdfBucket(){
        List<Pdf> allPdfList = pdfJpaRepository.findAll();
        String keyPrefix = "pdf/";
        String subPath = "/pdf";

        List<String> allPdfNameList = new ArrayList<>();
        if(!allPdfList.isEmpty()){
            for(Pdf pdf : allPdfList){
                String[] splitedPath = pdf.getPath().split("[/]");
                String pdfName = splitedPath[splitedPath.length-1];
                allPdfNameList.add(keyPrefix+pdfName);
            }
            s3api.delete(allPdfNameList,subPath);
            pdfJpaRepository.deleteAll();
        }
    }
}
