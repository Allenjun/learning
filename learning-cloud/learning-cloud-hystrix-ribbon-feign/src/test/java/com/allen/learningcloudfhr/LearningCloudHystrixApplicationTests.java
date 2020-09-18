package com.allen.learningcloudfhr;

import com.allen.learningcloudfhr.hystrix.AnnotationHystrix;
import com.allen.learningcloudfhr.hystrix.ClassHystrix;
import com.netflix.loadbalancer.BaseLoadBalancer;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningCloudHystrixApplicationTests {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AnnotationHystrix annotationHystrix;
    @Autowired
    BaseLoadBalancer baseLoadBalancer;

    @Test
    public void ribbon() {
        String demo = baseLoadBalancer.choose(null);
        System.out.println(demo);
    }

    @Test
    public void contextLoads() {
        long l = System.currentTimeMillis();
        String s = annotationHystrix.get();
        System.out.println(s);
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    @SneakyThrows
    public void test() {
        ClassHystrix classHystrix = new ClassHystrix(restTemplate);
        Observable<String> observe = classHystrix.observe();
        observe.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("调用成功");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                System.out.println("调用失败");
            }

            @Override
            public void onNext(String s) {
                System.out.println("获得响应数据: " + s);
            }
        });
        Thread.sleep(5000);
    }
}
