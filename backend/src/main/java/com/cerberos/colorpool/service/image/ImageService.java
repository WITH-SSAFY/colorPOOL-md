package com.cerberos.colorpool.service.image;

import com.cerberos.colorpool.advice.exception.CImageNotUploadException;
import com.cerberos.colorpool.entity.image.Image;
import com.cerberos.colorpool.repository.image.ImageJpaRepository;
import com.cerberos.colorpool.s3.S3api;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageJpaRepository imageJpaRepository;
    private final S3api s3api;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String imageFolder = "http://k3a501.p.ssafy.io/resource/image/";

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
            logger.info("a new image(path : "+image.getPath()+") is added successfully");
        }catch (Exception e){
            logger.info("failed to add a new image");
            throw new CImageNotUploadException();
        }
        return newImagePath;
    }

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
        if(extention.equalsIgnoreCase("jpeg")){
            extention ="jpg";
        }
        return extention;
    }

}
