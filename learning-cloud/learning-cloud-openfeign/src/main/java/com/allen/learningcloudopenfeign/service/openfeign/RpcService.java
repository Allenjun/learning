package com.allen.learningcloudopenfeign.service.openfeign;

import com.allen.web.vo.ArticleDetailVO;
import com.allen.web.vo.Resp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
        name = "learning-cloud-service",
//        fallback = RpcServiceFallback.class
        fallbackFactory = RpcFallbackFactory.class
)
public interface RpcService {

    @GetMapping("/bigload")
    String bigload();

    @GetMapping("/throwable")
    String throwable();

    @GetMapping("/info")
    String info();

    @GetMapping("/cloudConfig")
    String cloudConfig();

    @GetMapping("/object")
    Resp<ArticleDetailVO> object();

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(MultipartFile file);

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<byte[]> download(@RequestParam("filename") String filename);

    @PostMapping(value = "/articleDetail")
    ArticleDetailVO articleDetail(@RequestBody ArticleDetailVO articleDetail);
}
