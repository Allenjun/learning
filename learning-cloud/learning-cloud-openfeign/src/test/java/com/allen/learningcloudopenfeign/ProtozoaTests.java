package com.allen.learningcloudopenfeign;

import com.allen.learningcloudopenfeign.service.feign.ProtozoaService;
import feign.Feign;
import org.junit.Test;

public class ProtozoaTests {

    @Test
    public void test1() {
        ProtozoaService target = Feign.builder()
                .target(ProtozoaService.class, "https://httpbin.org");
        System.out.println(target.get());
    }
}
