package com.buka.service.impl;

import com.buka.dto.MinIoProperties;
import com.buka.entity.R;
import com.buka.service.MinIoService;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MinIoServiceImpl implements MinIoService {
    @Autowired
    private MinIoProperties minIoProperties;


    @Autowired
    private MinioClient minioClient;

    @Override
    public R uploadFile(MultipartFile file) {
        try {
            String uuid = UUID.randomUUID().toString();
            minioClient.putObject("0410bucket119",file.getOriginalFilename(),file.getInputStream(),"txt");
        } catch (InvalidBucketNameException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoResponseException e) {
            throw new RuntimeException(e);
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        } catch (ErrorResponseException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(e);
        }
        return R.ok();
    }


    // 可选的请求参数，例如设置响应内容类型
    private static Map<String, String> getPresignedRequestParameters() {
        Map<String, String> reqParams = new HashMap<>();
        // 例如：设置响应内容类型
         reqParams.put("Content-Disposition", "attachment; filename=\"example.txt\"\n");
         reqParams.put("Content-Type", "application/octet-stream");
        return reqParams;
    }

    @Override
    public R downLoadFile(String bucketName, String objName, HttpServletResponse response) {

        try ( InputStream inputStream = minioClient.getObject(bucketName, objName);   // 从文件服务器读取到的数据
              ServletOutputStream outputStream = response.getOutputStream();  //返回的输出流
                ) {
            // 设置返回数据的类型
            response.setContentType("application/octet-stream");   // 返回的是一个文件

            //设置返回头   filename  决定了，下载之后文件的名称buil
            response.setHeader("Content-Disposition","attachment; filename=xxx.txt");
            byte[] bs=new byte[1024];
            int len=0;
            while (-1!=(len=inputStream.read(bs))){
                outputStream.write(bs,0,len);
            }
        } catch (InvalidBucketNameException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoResponseException e) {
            throw new RuntimeException(e);
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        } catch (ErrorResponseException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(e);
        }
        return R.ok();
    }
}
