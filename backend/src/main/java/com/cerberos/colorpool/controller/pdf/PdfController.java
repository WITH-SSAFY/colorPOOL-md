package com.cerberos.colorpool.controller.pdf;

import com.cerberos.colorpool.model.pdf.PdfModel;
import com.cerberos.colorpool.service.pdf.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/pdf")
public class PdfController {
    private final PdfService pdfService;

    @PostMapping("/save")
    public String savePDF(@RequestBody PdfModel pdfModel){
        System.out.println(pdfModel.getContents());
        return pdfService.savePDF(pdfModel);
    }
}
