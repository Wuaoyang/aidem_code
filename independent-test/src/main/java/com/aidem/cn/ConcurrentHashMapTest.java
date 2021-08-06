package com.aidem.cn;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    private static final ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(16);

    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //修改次值可调整线程2执行次数
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (map) {
                    // 抢到锁后线程2是否会停止
                    System.out.println("Thread1拿到map锁");
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thread1 --------- " + i);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("准备执行map.put(...)");
                    map.put(i,i);
                    System.out.println("Thread2 ---------- " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}

