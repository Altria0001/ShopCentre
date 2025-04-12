package com.buka.service;

import com.buka.entity.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface MinIoService {

    public R uploadFile( MultipartFile file);


    public R downLoadFile(String bucketName, String objName, HttpServletResponse response);


}
