package com.cerberos.colorpool.service.image;

import com.cerberos.colorpool.advice.exception.CImageNotUploadException;
import com.cerberos.colorpool.entity.image.Image;
import com.cerberos.colorpool.entity.pdf.Pdf;
import com.cerberos.colorpool.repository.image.ImageJpaRepository;
import com.cerberos.colorpool.s3.S3api;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageJpaRepository imageJpaRepository;
    private final S3api s3api;

    private final String imageFolderPath = "../../colorpoolmd/image/";
    //private final String imageFolder = "http://k3a501.p.ssafy.io/api-image/";
    private final String imageFolder = "https://colorpool-md.s3.ap-northeast-2.amazonaws.com/image/";

    public String uploadImage(MultipartFile imageMultipartFile){
        String extension = getExtension(imageMultipartFile.getContentType());
        String newImageName = getNewImageName(extension);
        String newImagePath = imageFolder+newImageName;

        try{
            s3api.upload(imageMultipartFile,"/image",newImageName);
            Image image = Image.builder()
                    .path(newImagePath)
                    .build();
            imageJpaRepository.save(image);
        }catch (Exception e){
            throw new CImageNotUploadException();
        }
        return newImagePath;
    }

//    public String uploadImageTest(){//MultipartFile imageMultipartFile
//        String newImageName = "batt.jpg"; //getNewImageName(extention);
//
//        Path path = Paths.get(imageFolderPath+newImageName);
//        String contentType = "application/jpeg";//getContentType(type);
//        byte[] content = null;
//        try {
//            content = Files.readAllBytes(path);
//        } catch (IOException e) {
//        }
//
//        MultipartFile imageMultipartFile = new MockMultipartFile(newImageName,
//                newImageName, contentType, content);
//
//        String extension = getExtension(contentType);
//        newImageName = getNewImageName(extension);
//        String newImagePath = imageFolder+newImageName;
//
//        try{
//            s3api.upload(imageMultipartFile,"/image",newImageName);
//            Image image = Image.builder()
//                    .path(newImagePath)
//                    .build();
//            imageJpaRepository.save(image);
//        }catch (Exception e){
//            throw new CImageNotUploadException();
//        }
//        return newImagePath;
//    }
    public String getNewImageName(String extension) {
        Optional<Image> lastImage = imageJpaRepository.findFirstByOrderByIdDesc();
        long newImageId = 1;
        if(lastImage.isPresent()){
            newImageId = lastImage.get().getId()+1;
        }
        String newImageName = newImageId+"."+extension;
        return newImageName;
    }

    private String getExtension(String contentType) {
        String extention = contentType.split("/")[1];
        if(extention.equals("jpeg")){
            extention ="jpg";
        }
        return extention;
    }

}
