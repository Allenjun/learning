package com.allen.learningbootkafka.service;

import com.allen.learningbootkafka.pojo.DemoMessage;
import com.allen.learningbootkafka.pojo.Group;
import com.allen.learningbootkafka.pojo.Topic;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author JUN
 * @Description TODO
 * @createTime 14:07
 */
@Slf4j
@Component
public class DemoConsumer {
    
    @KafkaListener(topics = Topic.DEMO, groupId = Group.LOG + Topic.DEMO)
    public void onMessageLog1(DemoMessage message) {
        log.info("[log-1][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
    
    @KafkaListener(topics = Topic.DEMO, groupId = Group.LOG + Topic.DEMO)
    public void onMessageLog2(ConsumerRecord consumerRecord) {
        log.info("[log-2][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), consumerRecord);
    }
    
//    @KafkaListener(topics = Topic.DEMO, groupId = Group.MEG + Topic.DEMO)
    public void onMessageMeg(DemoMessage message, Acknowledgment acknowledgment) {
        log.info("[meg][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        acknowledgment.acknowledge();
    }
    
    @KafkaListener(topics = Topic.DEMO, groupId = Group.MAIL + Topic.DEMO)
    public void onMessageMail(DemoMessage message) {
        log.info("[mail][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
    
    @KafkaListener(topics = Topic.DEMO, groupId = Group.LOG + Topic.DEMO + "#{T(java.util.UUID).randomUUID()}")
    public void onMessageLogBroadcast(DemoMessage message) {
        log.info("[logBroadcast][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
    
    //    @KafkaListener(topics = Topic.DEMO, groupId = Group.SALE + Topic.DEMO)
    public void onMessageSale(DemoMessage message) {
        if (Boolean.TRUE) {
            throw new RuntimeException("[sale][线程编号:{} 消息异常]");
        }
        log.info("[sale][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
    
}
