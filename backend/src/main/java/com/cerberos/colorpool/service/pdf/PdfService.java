package com.cerberos.colorpool.service.pdf;

import com.cerberos.colorpool.entity.pdf.Pdf;
import com.cerberos.colorpool.model.pdf.PdfModel;
import com.cerberos.colorpool.repository.pdf.PdfJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final PdfJpaRepository pdfJpaRepository;

    @Transactional
    public String savePDF(PdfModel pdfModel){
        String result ="";

        pdfModel.setPath(getNewPDFpath());
        //pdf 저장


        Pdf new_pdf = Pdf.builder()
                .contents(pdfModel.getContents())
                .path(pdfModel.getPath())
                .build();

        pdfJpaRepository.save(new_pdf);
        return result;
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

    public String getNewPDFpath(){
        String newPDFpath = "";
        //count 쿼리문 실행
        long nextPDFid = pdfJpaRepository.count()+1;
        newPDFpath += "../../colorpoolmd/pdf/"+Long.toString(nextPDFid);
        return newPDFpath;
    }

}
