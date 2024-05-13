package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller for handling file uploads to Alibaba Cloud's Object Storage Service (OSS).
 * This controller provides an endpoint for uploading files directly to OSS.
 */
@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    /**
     * Handles the uploading of a file to Alibaba Cloud OSS.
     * The file is uploaded via MultipartFile parameter, and upon successful upload,
     * the URL to access the file on OSS is returned.
     *
     * @param image The MultipartFile object that contains the file to be uploaded.
     * @return Result containing the URL of the uploaded file if successful.
     * @throws IOException If an error occurs during file upload.
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("Uploading file, Filename: {}", image.getOriginalFilename());

        // Utilizes AliOSSUtils to upload the file to Alibaba Cloud OSS
        String url = aliOSSUtils.upload(image);
        log.info("File upload completed, Access URL: {}", url);

        return Result.success(url);
    }

}
