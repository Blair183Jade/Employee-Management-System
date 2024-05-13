package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Utility class for uploading files to Alibaba Cloud's Object Storage Service (OSS).
 * This class handles the creation of OSS clients and manages the uploading of files
 * to a specified bucket.
 */
@Component
public class AliOSSUtils {

    @Autowired
    private AliOSSProperties aliOSSProperties;

    /**
     * Uploads a file to Alibaba Cloud OSS and returns the file's URL.
     * The file is stored with a unique filename to prevent overwriting existing files.
     *
     * @param file The MultipartFile to be uploaded.
     * @return The URL of the uploaded file accessible from OSS.
     * @throws IOException If there is an error obtaining the file's input stream.
     */
    public String upload(MultipartFile file) throws IOException {
        // Retrieve OSS configuration details
        String endpoint = aliOSSProperties.getEndpoint();
        String accessKeyId = aliOSSProperties.getAccessKeyId();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
        String bucketName = aliOSSProperties.getBucketName();

        // Get an input stream of the file
        InputStream inputStream = file.getInputStream();

        // Create a unique filename to avoid overwriting existing files
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // Create an OSS client instance
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // Upload the file to OSS
            ossClient.putObject(bucketName, fileName, inputStream);

            // Construct the URL to access the uploaded file
            String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
            return url;
        } finally {
            // Ensure the OSS client is closed after the upload is complete
            ossClient.shutdown();
        }
    }
}
