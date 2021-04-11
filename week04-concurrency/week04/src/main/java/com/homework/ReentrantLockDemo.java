package com.homework;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: week04
 * @description:
 * @author: 王小欢
 * @create: 2021-04-11 16:14
 **/
public class ReentrantLockDemo {

    private static int result = 0;

    public static void main(String[] args) {
        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        Thread thread = new Thread(() -> {
            reentrantLock.lock();
            try {
                result = sum();
                condition.signalAll();
            } finally {
                reentrantLock.unlock();
            }
        }, "thread1");
        thread.start();
        reentrantLock.lock();
        try {
            condition.await();
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为："+result);

            System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");


        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        // 然后退出main线程
    }


    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2){
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }
}
