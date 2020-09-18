package com.allen.learningbootrocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.util.List;

@Slf4j
public class ConsumeTests {

    @Test
    public void contextLoads() throws MQClientException, InterruptedException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "please_rename_unique_group_name");
        consumer.setNamesrvAddr("192.168.30.130:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicTest", "*");
        consumer.registerMessageListener(
                new MessageListenerConcurrently() {

                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(
                            List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                        log.info("{} Receive New Messages: {}", Thread.currentThread().getName(), msgs);
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                });
        consumer.start();
        Thread.sleep(5000);
        consumer.shutdown();
    }
}
