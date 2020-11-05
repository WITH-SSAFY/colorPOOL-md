package com.cerberos.colorpool.controller.pdf;

import com.cerberos.colorpool.model.pdf.PdfModel;
import com.cerberos.colorpool.repository.response.SingleResult;
import com.cerberos.colorpool.service.ResponseService;
import com.cerberos.colorpool.service.pdf.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class PdfController {
    private final PdfService pdfService;
    private final ResponseService responseService;

    @Operation(summary = "마크다운 및 pdf저장", description = "사용자가 작성한 마크다운의 내용을 저장하고 이를 pdf로 변환하여 저장한 후 pdf저장 경로를 반환합니다.")
    @PostMapping("/pdf")
    public SingleResult<String> uploacPdf(@RequestBody PdfModel pdfModel){
        return responseService.getSingleResult(pdfService.uploadPdf(pdfModel));
    }

    @Operation(summary = "로컬 이미지 업로드", description = "사용자가 업로드한 로컬 이미지를 저장하고 이미지 경로를 반환합니다.")
    @PostMapping("/image")
    public SingleResult<String> uploadImage(){ //@RequestParam MultipartFile imageMultipartFile
        return responseService.getSingleResult(pdfService.uploadImage());
    }
}
