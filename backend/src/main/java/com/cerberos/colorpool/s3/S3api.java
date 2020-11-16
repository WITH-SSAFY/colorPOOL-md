package com.cerberos.colorpool.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class S3api {
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String upload(MultipartFile file, String subPath, String fileName) throws IOException {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(file.getSize());
        String realPath = bucket+subPath;

        s3Client.putObject(new PutObjectRequest(realPath, fileName, file.getInputStream(), meta)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(bucket, fileName).toString();
    }

    public void delete(List<String> fileNameList, String subPath){
        ArrayList<KeyVersion> keys = new ArrayList<>();
        for (String fileName : fileNameList){
            keys.add(new KeyVersion(fileName));
        }
        DeleteObjectsRequest multiObjectDeleteRequest = new DeleteObjectsRequest(bucket+subPath)
                .withKeys(keys).withQuiet(false);
        DeleteObjectsResult delObjRes = s3Client.deleteObjects(multiObjectDeleteRequest);
    }
}
