package com.allen;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.internals.StickyPartitionCache;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.utils.Utils;

import java.util.Map;

public class HotPointPartitioner implements Partitioner {

    private final StickyPartitionCache stickyPartitionCache = new StickyPartitionCache();

    @Override
    public int partition(
            String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        Integer numPartitions = cluster.partitionCountForTopic(s);
        if (o == null) {
            return this.stickyPartitionCache.partition(s, cluster);
        }
        if (numPartitions == 1) {
            return 0;
        }
        if (o instanceof String && ((String) o).startsWith("hotkey_")) {
            return numPartitions - 1;
        }
        return Utils.toPositive(Utils.murmur2(bytes)) % numPartitions - 1;
    }

    @Override
    public void close() {
    }

    @Override
    public void onNewBatch(String topic, Cluster cluster, int prevPartition) {
        this.stickyPartitionCache.nextPartition(topic, cluster, prevPartition);
    }

    @Override
    public void configure(Map<String, ?> map) {
    }
}
