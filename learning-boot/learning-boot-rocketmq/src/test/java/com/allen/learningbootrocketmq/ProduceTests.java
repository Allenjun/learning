package com.allen.learningbootrocketmq;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
public class ProduceTests {

    /**
     * @description: 同步方式发送消息
     * @params
     * @return:
     * @throw
     */
    @Test
    public void syncSend()
            throws MQClientException, RemotingException, InterruptedException, MQBrokerException,
            UnsupportedEncodingException {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr("192.168.30.130:9876");
        producer.start();

        //        producer.createTopic(producer.getCreateTopicKey(), "TopicTest", 1);
        Message message =
                new Message("TopicTest", "tagsA", "Hello MQ".getBytes(RemotingHelper.DEFAULT_CHARSET));
        SendResult result = producer.send(message);
        log.info("{}", result);

        Thread.sleep(5000);
        producer.shutdown();
    }

    /**
     * @description: 异步方式发送消息
     * @params
     * @return:
     * @throw
     */
    @Test
    @SneakyThrows
    public void asyncSend() {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name_4");
        producer.setNamesrvAddr("192.168.30.130:9876");
        producer.start();
        Message message =
                new Message("TopicTest", "tagsA", "Hello MQ".getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.send(
                message,
                new SendCallback() {

                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("{}", sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        log.info("{}", throwable);
                    }
                });
        Thread.sleep(5000);
        producer.shutdown();
    }

    /**
     * @description: oneway消息发送，允许消息的丢失，发送消息而不会收到broker的应答
     * @params
     * @return:
     * @throw
     */
    @Test
    @SneakyThrows
    public void onewaySend() {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name_4");
        producer.setNamesrvAddr("192.168.30.130:9876");
        producer.start();
        Message message =
                new Message("TopicTest", "tagsA", "Hello MQ".getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.sendOneway(message);
        Thread.sleep(5000);
        producer.shutdown();
    }

    @Test
    @SneakyThrows
    public void queueSelectorSend() {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name_4");
        producer.setNamesrvAddr("192.168.30.130:9876");
        producer.start();
        Message message =
                new Message(
                        "TopicTest",
                        "tagsA",
                        String.valueOf(System.currentTimeMillis()),
                        "Hello MQ".getBytes(RemotingHelper.DEFAULT_CHARSET));
        SendResult result =
                producer.send(
                        message,
                        new MessageQueueSelector() {
                            @Override
                            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                                return mqs.get(0);
                            }
                        },
                        "");

        log.info("{}", result);
        Thread.sleep(5000);
        producer.shutdown();
    }
}
