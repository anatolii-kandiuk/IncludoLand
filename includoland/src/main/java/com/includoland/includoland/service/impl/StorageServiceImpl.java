package com.includoland.includoland.service.impl;

import com.includoland.includoland.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    private final S3Client s3Client;

    @Value("${cloud.r2.bucket-name}")
    private String bucketName;

    @Value("${cloud.r2.public-url}")
    private String publicUrl;

    public StorageServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String uploadFile(MultipartFile file, String folder) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID() + "-" + originalFilename;
        
        String key = folder + "/" + uniqueFilename;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, 
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return String.format("%s/%s", publicUrl, key);
    }
}