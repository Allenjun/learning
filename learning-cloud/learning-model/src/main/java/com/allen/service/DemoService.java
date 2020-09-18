package com.allen.service;

import com.allen.web.vo.ArticleDetailVO;
import com.allen.web.vo.Resp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface DemoService {

    default String bigload() {
        return null;
    }

    default String throwable() {
        return null;
    }

    default String info() {
        return null;
    }

    default String cloudConfig() {
        return null;
    }

    default Resp<ArticleDetailVO> object() {
        return null;
    }

    default String upload(MultipartFile file) {
        return null;
    }

    default ResponseEntity<byte[]> download(String filename) {
        return null;
    }

    default ArticleDetailVO articleDetail(ArticleDetailVO articleDetail) {
        return null;
    }
}
