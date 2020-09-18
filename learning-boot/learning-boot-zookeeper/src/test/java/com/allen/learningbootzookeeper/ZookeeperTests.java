package com.allen.learningbootzookeeper;

import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ZookeeperTests {

    private int runCount = 3;
    private String persistent = "/" + runCount + "persistent";
    private String ephemeral = "/" + runCount + "ephemeral";
    private String persistent_sequential = "/persistent_sequential-";
    private String ephemeral_sequential = "/ephemeral_sequential-";

    /**
     * @description: 命名服务，在指定路径创建服务名作为Znode的节点名,值为服务地址
     */
    @Test
    @SneakyThrows
    public void testNamingService() {
        Map<String, String> serviceNamingMap = Maps.newLinkedHashMap();
        serviceNamingMap.put("/namingService", "");
        serviceNamingMap.put("/namingService/baidu1", "https://www.baidu.com/");
        serviceNamingMap.put("/namingService/google1", "https://www.google.com/");
        ZooKeeper client = getClient();
        serviceNamingMap.forEach(
                (k, v) -> {
                    try {
                        client.create(
                                k, v.getBytes(StandardCharsets.UTF_8), Ids.OPEN_ACL_UNSAFE,
                                CreateMode.PERSISTENT);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                });
    }

    /**
     * @description: 配置中心，利用zookeeper维护一个分成命名空间，Znode的值就是配置数据
     */
    @Test
    @SneakyThrows
    public void testCloudConfig() {
        Map<String, String> cloudConfigMap = Maps.newLinkedHashMap();
        cloudConfigMap.put("/ftp", "");
        cloudConfigMap.put("/ftp/host", "192.168.1.12");
        cloudConfigMap.put("/ftp/username", "root");
        cloudConfigMap.put("/ftp/password", "123456");
        cloudConfigMap.put("/server", "");
        cloudConfigMap.put("/server/port", "8080");
        cloudConfigMap.put("/server/address", "localhost");
        ZooKeeper client = getClient();
        cloudConfigMap.forEach(
                (k, v) -> {
                    try {
                        client.create(
                                k, v.getBytes(StandardCharsets.UTF_8), Ids.OPEN_ACL_UNSAFE,
                                CreateMode.PERSISTENT);
                    } catch (KeeperException e) {
                        if (e.code().equals(Code.NONODE)) {
                            log.warn("cloud config <{}> exist!", k);
                        }
                        log.error("", e);
                    } catch (Exception e) {
                        log.error("", e);
                    }
                });
    }

    /**
     * @description: 服务注册与发现，服务提供者在/providers目录下创建自己的url的节点； 服务消费者在/consumers目录下创建自己的url的节点，并订阅/providers目录
     * 监控中心订阅/providers和/consumers
     */
    @Test
    @SneakyThrows
    public void testserviceRegistry() {
        ZooKeeper client = getClient();
    }

    /**
     * @description: 分布式锁： 1. 独占锁：创建/删除临时节点，实现锁的获取和释放 2. 顺序锁：创建顺序临时节点，监听自己的节点是否是最小的节点，是则获得锁；不是就监听前一个节点的删除操作
     */
    @Test
    @SneakyThrows
    public void testDistributedLock() {
        ZooKeeper client = getClient();
    }

    @Test
    @SneakyThrows
    public void testCreate() {
        ZooKeeper client = getClient();

        client.create(persistent, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        client.create(ephemeral, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        client.create(
                persistent_sequential, new byte[0], Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT_SEQUENTIAL);
        client.create(
                ephemeral_sequential, new byte[0], Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    @Test
    @SneakyThrows
    public void testGet() {
        ZooKeeper client = getClient();
        Stat stat = new Stat();
        byte[] data = client.getData(persistent, false, stat);
        log.info("get {} data: {}, Stat: {}", persistent, new String(data), stat);

        client.getData(
                persistent,
                event -> {
                    if (event.getType().equals(EventType.NodeDeleted)) {
                        log.info("znode {} delete!!!", persistent);
                    }
                },
                stat);
        testDelete();
        testCreate();
        testDelete();
    }

    @Test
    @SneakyThrows
    public void testDelete() {
        ZooKeeper client = getClient();
        client.delete(persistent, -1);
    }

    @Test
    @SneakyThrows
    public void testUpdate() {
        ZooKeeper client = getClient();
        Stat stat = client.setData(persistent, new byte[0], 0);
        log.info("{}", stat.getVersion());
    }

    @Test
    @SneakyThrows
    public void testExist() {
        ZooKeeper client = getClient();
    }

    private ZooKeeper getClient() throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        ZooKeeper zk =
                new ZooKeeper(
                        "139.9.218.43:2181",
                        3000,
                        watchedEvent -> {
                            if (watchedEvent.getState() == KeeperState.SyncConnected) {
                                latch.countDown();
                            }
                        });
        latch.await(); // ZooKeeper不会等待连接成功才返回对象，所以要加CountDownLatch保证连接成功才进行余下操作
        return zk;
    }
}
