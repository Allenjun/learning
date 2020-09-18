package com.allen.learningcloudservice.controller;

import com.allen.service.DemoService;
import com.allen.web.vo.ArticleDetailVO;
import com.allen.web.vo.Resp;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author JUN @Description TODO
 * @createTime 21:27
 */
@Slf4j
@RestController
public class IndexController {

    @Autowired
    DemoService demoService;

    @SneakyThrows
    @GetMapping("/bigload")
    public String bigload() {
        return demoService.bigload();
    }

    @GetMapping("/throwable")
    public String throwable() {
        return demoService.throwable();
    }

    @GetMapping("/info")
    public String info() {
        return demoService.info();
    }

    @GetMapping("/config/author")
    public String cloudConfig() {
        return demoService.cloudConfig();
    }

    @GetMapping("/object")
    public Resp<ArticleDetailVO> object() {
        return demoService.object();
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        return demoService.upload(file);
    }

    @PostMapping("/articleDetail")
    public ArticleDetailVO articleDetail(@RequestBody ArticleDetailVO articleDetail) {
        return demoService.articleDetail(articleDetail);
    }
}
