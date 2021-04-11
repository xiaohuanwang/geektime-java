package com.concurrency;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: week04
 * @description:
 * @author: 王小欢
 * @create: 2021-04-04 23:33
 **/
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread t=Thread.currentThread();
            System.out.println("当前线程"+t.getName());
        };

        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        thread.setDaemon(true);
        thread.start();

        Thread.sleep(4000);

        Map map = new HashMap();
        map.put("123","123");
    }




}
