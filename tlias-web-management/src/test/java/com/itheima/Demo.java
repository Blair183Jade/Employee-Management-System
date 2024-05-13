package com.itheima;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;

import java.io.FileInputStream;
import java.io.InputStream;

public class Demo {

    public static void main(String[] args) throws Exception {
        // Define the endpoint. Replace "oss-cn-hangzhou.aliyuncs.com" with your OSS region endpoint.
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";

        // Use environment variables or a configuration management solution for sensitive keys.
        // Placeholder keys for demonstration purposes; replace with your actual keys
        String accessKeyId = "LTAI4GCH1vX6DKqJWxd6nEuW";
        String accessKeySecret = "yBshYweHOpqDuhCArrVHwIiBKpyqSL";

        // Specify the name of your bucket
        String bucketName = "web-tlias";

        // Specify the full path within the bucket and the local file path.
        String objectName = "1.jpg";
        String filePath = "C:\\Users\\Administrator\\Pictures\\Camera Roll\\1.jpg";

        // Create an instance of the OSS client.
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // Create an input stream from the local file.
            InputStream inputStream = new FileInputStream(filePath);

            // Upload the file to OSS using the input stream.
            ossClient.putObject(bucketName, objectName, inputStream);
            System.out.println("Upload successful.");
        } catch (OSSException oe) {
            System.err.println("Caught an OSSException:");
            System.err.println("Error Message: " + oe.getErrorMessage());
            System.err.println("Error Code: " + oe.getErrorCode());
            System.err.println("Request ID: " + oe.getRequestId());
            System.err.println("Host ID: " + oe.getHostId());
        } catch (ClientException ce) {
            System.err.println("Caught a ClientException:");
            System.err.println("Error Message: " + ce.getMessage());
        } finally {
            // Ensure the OSS client is closed.
            if (ossClient != null) {
                ossClient.shutdown();
                System.out.println("OSS client shutdown.");
            }
        }
    }
}
