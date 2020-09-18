package com.allen;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

@Slf4j
public class AllenConsumerRebalanceListener implements ConsumerRebalanceListener {

    private Consumer consumer;

    public AllenConsumerRebalanceListener(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        log.info("PartitionsRevoked...");
        consumer.commitSync();
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        log.info("PartitionsAssigned...");
        for (TopicPartition partition : partitions) {
            //            consumer.seek(partition, getOffsetFromDB(partition));
        }
    }

    private long getOffsetFromDB(TopicPartition partition) {
        return 0;
    }
}
