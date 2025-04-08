package com.buka.config;


import com.buka.dto.MinIoProperties;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIoConfig {

    @Autowired
    private MinIoProperties minIoProperties;


    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        MinioClient minioClient = new MinioClient(minIoProperties.getUrl(),minIoProperties.getUserName(),minIoProperties.getPassWord());
        return minioClient;
    }

}

