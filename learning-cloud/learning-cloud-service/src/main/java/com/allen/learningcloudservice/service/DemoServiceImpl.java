package com.allen.learningcloudservice.service;

import com.allen.learningcloudservice.config.Info;
import com.allen.service.DemoService;
import com.allen.web.CodeStatus;
import com.allen.web.vo.ArticleDetailVO;
import com.allen.web.vo.Resp;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    Info info;

    @Override
    @SneakyThrows
    public String bigload() {
        Thread.sleep(5000);
        return "bigload";
    }

    @Override
    public String throwable() {
        throw new RuntimeException();
    }

    @Override
    public String info() {
        return info.getAppname() + ":" + info.getPort();
    }

    @Override
    public String cloudConfig() {
        return info.getAuthor();
    }

    @Override
    public Resp<ArticleDetailVO> object() {
        return new Resp<ArticleDetailVO>(
                CodeStatus.SUCCESS,
                new ArticleDetailVO("allen", "18.5", "love you", "no pain no game", new Date()));
    }

    @Override
    public String upload(MultipartFile file) {
        return file.getOriginalFilename();
    }

    @Override
    public ResponseEntity<byte[]> download(String filename) {
        try {
            File file = new File(filename);
            byte[] bytes = FileUtil.readAsByteArray(file);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String encode = new String(file.getName().getBytes("gbk"), "iso8859-1");
            headers.set("Content-Disposition", "attachment;filename=" + encode);
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error("下载出错", e);
            return null;
        }
    }

    @Override
    public ArticleDetailVO articleDetail(ArticleDetailVO articleDetailVO) {
        return articleDetailVO;
    }
}
