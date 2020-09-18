package com.allen.learningbootzookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/07/22 17:34:00
 */
public class CuratorTests {

    @Test
    public void test1() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.newClient("127.0.0.1:2181", 5000, 3000, retryPolicy);
        client.start();

        InterProcessMutex interProcessMutex = new InterProcessMutex(client, "/lock");
        try {
            interProcessMutex.acquire();
            // TODO 同步代码

        } finally {
            interProcessMutex.release();
        }
    }
}
