package com.cerberos.colorpool.controller.image;

import com.cerberos.colorpool.model.response.SingleResult;
import com.cerberos.colorpool.service.ResponseService;
import com.cerberos.colorpool.service.image.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class ImageController {
    private final ImageService imageService;
    private final ResponseService responseService;

    @Operation(summary = "로컬 이미지 업로드", description = "사용자가 업로드한 로컬 이미지를 저장하고 이미지 경로를 반환합니다.")
    @PostMapping("/image")
    public SingleResult<String> uploadImage(@RequestParam MultipartFile imageMultipartFile){ //@RequestParam MultipartFile imageMultipartFile
        //return responseService.getSingleResult(imageService.uploadImageTest());
        return responseService.getSingleResult(imageService.uploadImage(imageMultipartFile));
    }
}
