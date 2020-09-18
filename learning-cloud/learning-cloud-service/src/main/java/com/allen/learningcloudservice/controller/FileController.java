package com.allen.learningcloudservice.controller;

import com.allen.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class FileController {

    @Autowired
    DemoService demoService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(String filename) {
        return demoService.download(filename);
    }
}
