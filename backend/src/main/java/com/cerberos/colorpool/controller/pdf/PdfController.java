package com.cerberos.colorpool.controller.pdf;

import com.cerberos.colorpool.model.pdf.PdfModel;
import com.cerberos.colorpool.repository.response.SingleResult;
import com.cerberos.colorpool.service.ResponseService;
import com.cerberos.colorpool.service.pdf.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/pdf")
public class PdfController {
    private final PdfService pdfService;
    private final ResponseService responseService;

    @Operation(summary = "마크다운 및 pdf저장", description = "사용자가 작성한 마크다운의 내용을 저장하고 이를 pdf로 변환하여 저장한다.")
    @PostMapping("/save")
    public SingleResult<Long> savePDF(@RequestBody PdfModel pdfModel){
        return responseService.getSingleResult(pdfService.savePDF(pdfModel));
    }
}
