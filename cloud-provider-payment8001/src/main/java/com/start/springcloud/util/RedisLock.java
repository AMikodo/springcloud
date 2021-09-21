package com.start.springcloud.util;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedisLock {

    public static void lock() {
        String lockKey = "paymentKey";
        long waitTimeout = 30;
        long leaseTime = 100;
        // 1.构造redisson实现分布式锁必要的Config
        Config config = new Config();
        config.useSingleServer().setAddress("redis://123.60.34.168:6379").setPassword("fs341225").setDatabase(0);
        // 2.构造RedissonClient
        RedissonClient redissonClient = Redisson.create(config);
        // 3.获取锁对象实例（无法保证是按线程的顺序获取到）
        RLock rLock = redissonClient.getLock(lockKey);
        try {
            boolean res = rLock.tryLock(waitTimeout, leaseTime, TimeUnit.SECONDS);
        } catch (Exception exception) {

        } finally {
            rLock.unlock();
        }

    }
}
