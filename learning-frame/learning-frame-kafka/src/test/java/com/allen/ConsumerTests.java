package com.allen;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import static com.allen.Constants.group_test;
import static com.allen.Constants.topic1;

@Slf4j
public class ConsumerTests {

    @Test
    @SneakyThrows
    public void consume() {
        Properties consumeProp = new Properties();
        consumeProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.1:9092", "192.168.30.1:9093", "192.168.30.1:9094"}));
        consumeProp.put("key.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("value.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("group.id", group_test); // 消费组名称
        consumeProp.put("auto.commit.interval.ms", null); // 自动提交的间隔时间
        consumeProp.put("enable.auto.commit", null); // 是否自动提交
        consumeProp.put("client.id", null); // 客户端id
        consumeProp.put("auto.offset.reset", null); // 当偏移量无效的时候，latest：从最新记录开始读取；earliest：从起始位置开始读取
        consumeProp.put("heartbeat.interval.ms", null); // 心跳检测间隔时间
        consumeProp.put("session.timeout.ms", null); // 消费者没有在指定时间内向协调器发送像心跳检测时，认死亡，触发再均衡
        consumeProp.put("isolation.level", null); // 事务等级，默认：读未提交
        consumeProp.put("fetch.min.bytes", null); // broker向消费者发送信息的最小数据量
        consumeProp.put(
                "fetch.max.wait.ms", null); // broker最多等待时间，超过此时间，无论是否到达 fetch.min.bytes，都向消费者发送信息
        consumeProp.put("partition.assignment.strategy", null); // 分片分配策略，Range、RoundRobin
        KafkaConsumer<String, Object> kafkaConsumer = new KafkaConsumer<>(consumeProp);
    }

    @Test
    @SneakyThrows
    public void consume1() {
        Properties consumeProp = new Properties();
        consumeProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.1:9092", "192.168.30.1:9093", "192.168.30.1:9094"}));
        consumeProp.put("key.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("value.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("group.id", group_test);
        KafkaConsumer<String, Object> kafkaConsumer = new KafkaConsumer<>(consumeProp);
        kafkaConsumer.subscribe(Arrays.asList(topic1));
        if (true) {
            while (true) {
                ConsumerRecords<String, Object> records = kafkaConsumer.poll(Duration.ofSeconds(2));
                for (ConsumerRecord<String, Object> record : records) {
                    log.info(
                            "topic:{}, partition:{}, offset:{}, message:{}",
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.value());
                }
            }
        }
        kafkaConsumer.close();
    }

    @Test
    @SneakyThrows
    public void consume2() {
        Properties consumeProp = new Properties();
        consumeProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.1:9092", "192.168.30.1:9093", "192.168.30.1:9094"}));
        consumeProp.put("key.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("value.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("group.id", group_test);
        KafkaConsumer<String, Object> kafkaConsumer = new KafkaConsumer<>(consumeProp);
        kafkaConsumer.subscribe(Arrays.asList(topic1));
        if (true) {
            while (true) {
                ConsumerRecords<String, Object> records = kafkaConsumer.poll(Duration.ofSeconds(2));
                for (ConsumerRecord<String, Object> record : records) {
                    log.info(
                            "topic:{}, partition:{}, offset:{}, message:{}",
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.value());
                }
            }
        }
        kafkaConsumer.close();
    }

    @Test
    @SneakyThrows
    public void consume3() {
        Properties consumeProp = new Properties();
        consumeProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.1:9092", "192.168.30.1:9093", "192.168.30.1:9094"}));
        consumeProp.put("key.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("value.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("group.id", group_test);
        consumeProp.put("enable.auto.commit", false);
        //        consumeProp.put("auto.offset.reset", "earliest");
        KafkaConsumer<String, Object> kafkaConsumer = new KafkaConsumer<>(consumeProp);
        kafkaConsumer.subscribe(Arrays.asList(topic1));
        try {
            if (true) {
                while (true) {
                    ConsumerRecords<String, Object> records = kafkaConsumer
                            .poll(Duration.ofSeconds(2));
                    for (ConsumerRecord<String, Object> record : records) {
                        log.info(
                                "topic:{}, partition:{}, offset:{}, key:{}, message:{}",
                                record.topic(),
                                record.partition(),
                                record.offset(),
                                record.key(),
                                record.value());
                    }
                    kafkaConsumer.commitAsync(
                            new OffsetCommitCallback() {
                                @Override
                                public void onComplete(
                                        Map<TopicPartition, OffsetAndMetadata> offsets,
                                        Exception exception) {
                                    if (exception != null) {
                                        log.error("Commit fail for offsets {}", offsets, exception);
                                    }
                                }
                            });
                }
            }
        } catch (Exception e) {
            log.error("", e);
            try {
                kafkaConsumer.commitSync();
            } finally {
                kafkaConsumer.close();
            }
        }
    }

    @Test
    @SneakyThrows
    public void consume4() {
        Properties consumeProp = new Properties();
        consumeProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.1:9092", "192.168.30.1:9093", "192.168.30.1:9094"}));
        consumeProp.put("key.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("value.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("group.id", group_test);
        consumeProp.put("enable.auto.commit", false);
        KafkaConsumer<String, Object> kafkaConsumer = new KafkaConsumer<>(consumeProp);
        kafkaConsumer.subscribe(
                Arrays.asList(topic1), new AllenConsumerRebalanceListener(kafkaConsumer));
        try {
            if (true) {
                while (true) {
                    ConsumerRecords<String, Object> records = kafkaConsumer
                            .poll(Duration.ofSeconds(2));
                    for (ConsumerRecord<String, Object> record : records) {
                        log.info(
                                "topic:{}, partition:{}, offset:{}, key:{}, message:{}",
                                record.topic(),
                                record.partition(),
                                record.offset(),
                                record.key(),
                                record.value());
                    }
                    kafkaConsumer.commitAsync(
                            new OffsetCommitCallback() {
                                @Override
                                public void onComplete(
                                        Map<TopicPartition, OffsetAndMetadata> offsets,
                                        Exception exception) {
                                    if (exception != null) {
                                        log.error("Commit fail for offsets {}", offsets, exception);
                                    }
                                }
                            });
                }
            }
        } catch (Exception e) {
            log.error("", e);
            try {
                kafkaConsumer.commitSync();
            } finally {
                kafkaConsumer.close();
            }
        }
    }

    @Test
    @SneakyThrows
    public void consume5() {

        Properties consumeProp = new Properties();
        consumeProp.put(
                "bootstrap.servers",
                Arrays.asList(
                        new String[]{"192.168.30.1:9092", "192.168.30.1:9093", "192.168.30.1:9094"}));
        consumeProp.put("key.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("value.deserializer", "com.allen.ObjectDeserializer");
        consumeProp.put("group.id", group_test);
        consumeProp.put("enable.auto.commit", false);
        KafkaConsumer<String, Object> kafkaConsumer = new KafkaConsumer<>(consumeProp);

        Thread mainThread = Thread.currentThread();
        Runtime.getRuntime()
                .addShutdownHook(
                        new Thread() {
                            @Override
                            public void run() {
                                log.warn("Stoping procedure...");
                                kafkaConsumer.wakeup();
                                try {
                                    mainThread.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

        kafkaConsumer.subscribe(
                Arrays.asList(topic1), new AllenConsumerRebalanceListener(kafkaConsumer));
        try {
            if (true) {
                while (true) {
                    ConsumerRecords<String, Object> records = kafkaConsumer
                            .poll(Duration.ofSeconds(2));
                    for (ConsumerRecord<String, Object> record : records) {
                        log.info(
                                "topic:{}, partition:{}, offset:{}, key:{}, message:{}",
                                record.topic(),
                                record.partition(),
                                record.offset(),
                                record.key(),
                                record.value());
                    }
                    kafkaConsumer.commitAsync(
                            new OffsetCommitCallback() {
                                @Override
                                public void onComplete(
                                        Map<TopicPartition, OffsetAndMetadata> offsets,
                                        Exception exception) {
                                    if (exception != null) {
                                        log.error("Commit fail for offsets {}", offsets, exception);
                                    }
                                }
                            });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            kafkaConsumer.commitSync();
        } finally {
            kafkaConsumer.close();
        }
    }
}
