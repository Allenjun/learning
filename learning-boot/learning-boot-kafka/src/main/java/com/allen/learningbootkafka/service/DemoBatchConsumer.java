package com.allen.learningbootkafka.service;

import com.allen.learningbootkafka.pojo.DemoMessage;
import com.allen.learningbootkafka.pojo.Group;
import com.allen.learningbootkafka.pojo.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

/**
 * @author JUN @Description TODO
 * @createTime 14:07
 */
@Slf4j
public class DemoBatchConsumer {

    @KafkaListener(topics = Topic.DEMO, groupId = Group.PAY + Topic.DEMO)
    public void onMessageLogBatch(List<DemoMessage> messages) {
        messages.forEach(
                message ->
                        log.info("[payBatch][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message));
    }
}
