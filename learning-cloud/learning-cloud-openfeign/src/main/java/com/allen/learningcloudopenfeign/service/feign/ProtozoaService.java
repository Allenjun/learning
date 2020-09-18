package com.allen.learningcloudopenfeign.service.feign;

import feign.RequestLine;

public interface ProtozoaService {

    @RequestLine("GET /get")
    String get();
}
