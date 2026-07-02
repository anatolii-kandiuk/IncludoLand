package com.includoland.includoland.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface StorageService {
    String uploadFile(MultipartFile file, String folder) throws IOException;
}