package com.allen.learningbootkafka;

import com.allen.learningbootkafka.pojo.DemoMessage;
import com.allen.learningbootkafka.pojo.Topic;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaOperations.OperationsCallback;
import org.springframework.kafka.core.KafkaOperations.ProducerCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningBootKafkaApplicationTests {
    
    @Autowired
    @Qualifier("kafkaTemplate")
    KafkaTemplate kafkaTemplate;
    
    @Autowired
    @Qualifier("kafkaTemplateInTransaction")
    KafkaTemplate kafkaTemplateInTransaction;
    
    @SuppressWarnings("unchecked")
    @Test
    public void test() throws ExecutionException, InterruptedException {
        DemoMessage demoMessage = new DemoMessage();
        demoMessage.setId(1);
        demoMessage.setMessage("我在");
        kafkaTemplate.execute(
            new ProducerCallback<Object, Object, Object>() {
                @Override
                public Object doInKafka(Producer producer) {
                    Future send = producer.send(new ProducerRecord<>(Topic.DEMO, demoMessage));
                    Object o = null;
                    try {
                        o = send.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    log.info("{}", o);
                    return null;
                }
            });
        Thread.sleep(10000);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void send() throws ExecutionException, InterruptedException {
        DemoMessage demoMessage = new DemoMessage();
        demoMessage.setId(1);
        demoMessage.setMessage("我在");
        ListenableFuture demo = kafkaTemplate.send(Topic.DEMO, demoMessage);
        
        System.out.println(demo.get());
        
        Thread.sleep(20000);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void sendInTransaction() throws ExecutionException, InterruptedException {
        DemoMessage demoMessage = new DemoMessage();
        demoMessage.setId(3);
        demoMessage.setMessage("我在");
        Object o =
            kafkaTemplate.executeInTransaction(
                new OperationsCallback<Object, Object, Object>() {
                    
                    @Override
                    public Object doInOperations(KafkaOperations<Object, Object> kafkaOperations) {
                        try {
                            SendResult<Object, Object> sendResult =
                                kafkaOperations.send(Topic.DEMO, demoMessage).get();
                            log.info(
                                "[sendInTransaction][发送编号：[{}] 发送结果：[{}]]", demoMessage.getId(), sendResult);
                            // 其它业务逻辑
                            return "success";
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        return "fail";
                    }
                });
        System.out.println(o);
        Thread.sleep(20000);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void asyncSend() throws ExecutionException, InterruptedException {
        DemoMessage demoMessage = new DemoMessage();
        demoMessage.setId(3);
        demoMessage.setMessage("我在");
        for (int i = 0; i < 10; i++) {
            kafkaTemplate
                .send(Topic.DEMO, demoMessage)
                .addCallback(
                    new ListenableFutureCallback<SendResult<Object, Object>>() {
                        
                        @Override
                        public void onSuccess(SendResult<Object, Object> result) {
                            log.info("[" + Topic.DEMO + "]发送成功：" + result.getRecordMetadata().offset());
                        }
                        
                        @Override
                        public void onFailure(Throwable ex) {
                            log.error("[" + Topic.DEMO + "]发送成功：" + ex.getMessage());
                        }
                    });
            Thread.sleep(2000);
        }
        
        Thread.sleep(20000);
    }
}
