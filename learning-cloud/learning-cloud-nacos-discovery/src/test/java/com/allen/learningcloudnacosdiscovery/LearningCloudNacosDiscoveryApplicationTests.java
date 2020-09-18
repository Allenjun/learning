package com.allen.learningcloudnacosdiscovery;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningCloudNacosDiscoveryApplicationTests {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LearningCloudNacosDiscoveryApplication.EchoService echoService;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Test
    public void test13() {
        ServiceInstance choose = loadBalancerClient.choose("learning-cloud-nacos-discovery");
        System.out.println(choose.getPort());
    }

    @Test
    public void test11() {
        String body =
                restTemplate
                        .getForEntity("http://learning-cloud-nacos-discovery/config/name", String.class)
                        .getBody();
        System.out.println(body);
    }

    @Test
    public void test12() {
        String config = echoService.config();
        System.out.println(config);
        String asd = echoService.echo("asd");
        System.out.println(asd);
    }

    @Test
    public void contextLoads() throws NacosException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", "127.0.0.1:8848");

        NamingService naming = NamingFactory.createNamingService(properties);

        naming.subscribe(
                "learning-cloud-nacos-discovery",
                new EventListener() {
                    @Override
                    public void onEvent(Event event) {
                        System.out.println(((NamingEvent) event).getServiceName());
                        System.out.println(((NamingEvent) event).getInstances());
                        System.out.println(((NamingEvent) event).getInstances().get(0).getIp());
                        System.out.println(((NamingEvent) event).getInstances().get(0).getPort());
                    }
                });
    }
}
