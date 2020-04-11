package com.fortunetree.basic.support.commons.business.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 资源ID锁
 *
 * 优势：synchronized只能锁对象的地址，所有像Long为1000的用户id是锁不住的
 * 此类来解决这个问题
 * synchronized (UserLockUtils.getLock("test" + userId)) {}
 * 使用时避免将id直接传入，避免多业务操作时相同id造成死锁
 * 全局的入参"接口名+userId"最好
 * @author ZhouJumbo
 */
public final class BoLock {
    private static ConcurrentHashMap<String, AtomicInteger> lockMap  = new ConcurrentHashMap<>();
    private BoLock(){}

    public static synchronized  AtomicInteger getLock(String key) {

        if (lockMap.get(key) == null) {// 当实体ID锁资源为空,初始化锁
            lockMap.putIfAbsent(key, new AtomicInteger(0));// 初始化一个竞争数为0的原子资源
        }
        int count = lockMap.get(key).incrementAndGet();// 线程得到该资源,原子性+1
        return lockMap.get(key);// 返回该ID资源锁
    }

    public static void giveUpMyLock(String key){
        if (lockMap.get(key) != null) {// 当实体ID资源不为空,才可以操作锁,防止抛出空指针异常
            int source = lockMap.get(key).decrementAndGet();// 线程释放该资源,原子性-1
            if (source <= 0) {// 当资源没有线程竞争的时候，就删除掉该锁,防止内存溢出
                lockMap.remove(key);
            }
        }
    }

}
