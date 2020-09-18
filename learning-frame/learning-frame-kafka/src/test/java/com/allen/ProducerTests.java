package com.allen;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

import static com.allen.Constants.default_value;
import static com.allen.Constants.topic1;

@Slf4j
public class ProducerTests {

    @Test
    @SneakyThrows
    public void client1() {
        Properties producerProp = new Properties();
        producerProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.107:9092", "192.168.30.107:9093", "192.168.30.107:9094"}));
        AdminClient adminClient = AdminClient.create(producerProp);
        adminClient.deleteTopics(Arrays.asList(topic1)).all().get();
    }

    @Test
    @SneakyThrows
    public void client2() {
        Properties producerProp = new Properties();
        producerProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.107:9092", "192.168.30.107:9093", "192.168.30.107:9094"}));
        AdminClient adminClient = AdminClient.create(producerProp);
        adminClient.createTopics(Arrays.asList(new NewTopic(topic1, 3, (short) 3))).all().get();
    }

    @Test
    @SneakyThrows
    public void produce1() {
        Properties producerProp = new Properties();
        producerProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.107:9092", "192.168.30.107:9093", "192.168.30.107:9094"}));
        producerProp
                .put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProp
                .put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<Object, Object> kafkaProducer = new KafkaProducer<>(producerProp);
        ProducerRecord producerRecord =
                new ProducerRecord(topic1, UUID.randomUUID().toString(), default_value);
        kafkaProducer.send(producerRecord);
        Thread.sleep(1000);
    }

    @Test
    @SneakyThrows
    public void produce2() {
        Properties producerProp = new Properties();
        producerProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.107:9092", "192.168.30.107:9093", "192.168.30.107:9094"}));
        producerProp
                .put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProp
                .put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<Object, Object> kafkaProducer = new KafkaProducer<>(producerProp);
        ProducerRecord producerRecord =
                new ProducerRecord(topic1, 2, UUID.randomUUID().toString(), default_value);
        kafkaProducer.send(producerRecord).get();
    }

    @Test
    @SneakyThrows
    public void produce3() {
        Properties producerProp = new Properties();
        producerProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.107:9092", "192.168.30.107:9093", "192.168.30.107:9094"}));
        producerProp
                .put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProp
                .put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProp.put("partitioner.class", "com.allen.HotPointPartitioner");
        KafkaProducer<Object, Object> kafkaProducer = new KafkaProducer<>(producerProp);
        //        ProducerRecord producerRecord = new ProducerRecord(topic1, hotkey_prefix + "123458",
        // default_value);
        ProducerRecord producerRecord = new ProducerRecord(topic1, "123458", default_value);
        kafkaProducer.send(
                producerRecord,
                new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (e != null) {
                            log.error("", e);
                            return;
                        }
                        log.info("分区ID：{}", recordMetadata.partition());
                        log.info("偏移量：{}", recordMetadata.offset());
                    }
                });
        Thread.sleep(2000);
    }

    @Test
    @SneakyThrows
    public void produce4() {
        Properties producerProp = new Properties();
        producerProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.107:9092", "192.168.30.107:9093", "192.168.30.107:9094"}));
        producerProp
                .put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProp
                .put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProp.put("partitioner.class", "com.allen.HotPointPartitioner"); // 自定义分区器
        producerProp.put("acks", 1); // 0：代表不需要等待来自服务器响应；1：代表需要信息到达首领节点；all：代表需要到达所有副本节点
        producerProp.put("buffer.memory", null); // 缓冲区大小，当待发送的信息大于发送速度时，导致缓存空间不足，进而引起阻塞或异常
        producerProp.put("max.block.ms", null); // 发送信息时可以阻塞的时间
        producerProp.put("compression.type", null); // 数据压缩算法
        producerProp.put("retries", null); // 重试次数
        producerProp.put("retry.backoff.ms", null); // 重试间隔
        producerProp.put("batch.size", null); // 一次批量发送的最大内存，并不是说要等到满足最大内存才发送，也就是说只限制最大值，没限制最小值
        producerProp.put("linger.ms", null); // 当没达到最大批处理内存时的等待时间
        producerProp.put(
                "max.in.flight.requests.per.connection",
                null); // 收到服务器响应前的最大可发送信息量，设置 1 代表信息顺序发送，即使开启重试
        producerProp.put("client.id", null); // 客户端id
        producerProp.put("interceptor.classes", null); // 拦截器
        producerProp.put("transaction.timeout.ms", null);
        producerProp.put("transactional.id", null);
        KafkaProducer<Object, Object> kafkaProducer = new KafkaProducer<>(producerProp);
    }

    @Test
    @SneakyThrows
    public void produce5() {
        Properties producerProp = new Properties();
        producerProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.107:9092", "192.168.30.107:9093", "192.168.30.107:9094"}));
        producerProp.put("key.serializer", "com.allen.ObjectSerializer");
        producerProp.put("value.serializer", "com.allen.ObjectSerializer");
        KafkaProducer<Object, Object> kafkaProducer = new KafkaProducer<>(producerProp);
        UserDel userDel = new UserDel("123", "allen");
        ProducerRecord producerRecord =
                new ProducerRecord(topic1, UUID.randomUUID().toString(), userDel);
        kafkaProducer.send(producerRecord).get();
    }

    @Test
    @SneakyThrows
    public void produce6() {
        Properties producerProp = new Properties();
        producerProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.107:9092", "192.168.30.107:9093", "192.168.30.107:9094"}));
        producerProp
                .put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProp
                .put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProp.put("transactional.id", "produce6");
        KafkaProducer<Object, Object> kafkaProducer = new KafkaProducer<>(producerProp);
        kafkaProducer.initTransactions();
        try {
            kafkaProducer.beginTransaction();
            kafkaProducer.send(new ProducerRecord(topic1, "123458", default_value));
            kafkaProducer.send(new ProducerRecord(topic1, "213333", default_value));
            //            int i = 1 / 0;
            kafkaProducer.commitTransaction();
        } catch (Exception e) {
            kafkaProducer.abortTransaction();
            throw e;
        }
    }
}
