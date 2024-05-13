package com.itheima.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties class for Alibaba Cloud OSS.
 * This class binds properties prefixed with "aliyun.oss" from the application's configuration files,
 * allowing easy access to these values throughout the application.
 * <p>
 * Properties include:
 * - endpoint: The OSS service endpoint.
 * - accessKeyId: The Access Key ID for OSS authentication.
 * - accessKeySecret: The Access Key Secret for OSS authentication.
 * - bucketName: The default bucket name used in the application.
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {
    private String endpoint;         // OSS service endpoint
    private String accessKeyId;      // Access Key ID for authentication
    private String accessKeySecret;  // Access Key Secret for authentication
    private String bucketName;       // Default bucket name
}
