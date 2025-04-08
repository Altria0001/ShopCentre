package com.buka.controller;


import com.buka.entity.R;
import com.buka.service.MinIoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private MinIoService minIoService;


    @RequestMapping("uploadFile")
    public R uploadFile(@RequestParam(name = "file") MultipartFile file){
        return minIoService.uploadFile(file);
    }

    @RequestMapping("downLoadFile")
    public R downLoadFile(String bucketName, String objName, HttpServletResponse response){
        return minIoService.downLoadFile(bucketName,objName,response);
    }

}
