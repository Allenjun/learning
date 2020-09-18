package com.allen.learningcloudopenfeign.service.openfeign;

import com.allen.web.vo.ArticleDetailVO;
import com.allen.web.vo.Resp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class RpcServiceFallback implements RpcService {

    @Override
    public String bigload() {
        return "fallback";
    }

    @Override
    public String throwable() {
        return "fallback";
    }

    @Override
    public String info() {
        return "fallback";
    }

    @Override
    public String cloudConfig() {
        return "fallback";
    }

    @Override
    public Resp<ArticleDetailVO> object() {
        return new Resp<ArticleDetailVO>();
    }

    @Override
    public String upload(MultipartFile file) {
        return "fallback";
    }

    @Override
    public ResponseEntity<byte[]> download(String filename) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ArticleDetailVO articleDetail(ArticleDetailVO articleDetail) {
        return new ArticleDetailVO();
    }
}
