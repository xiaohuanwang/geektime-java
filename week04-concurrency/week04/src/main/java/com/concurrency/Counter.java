package com.concurrency;

/**
 * @program: week04
 * @description: 线程计数器
 * @author: 王小欢
 * @create: 2021-04-06 15:17
 **/
public class Counter {
    private int sum=0;
    private synchronized void incr(){
        sum=sum+1;
    }
    private int getSum(){
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {

        int loop=10000;
        Counter counter1=new Counter();
        for (int i=0;i<loop;i++){
            counter1.incr();
        }
        System.out.println("单线程:"+counter1.getSum());
        Counter counter2=new Counter();
        Thread thread1 = new Thread(() -> {
            for (int i=0;i<loop/2;i++){
                counter2.incr();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i=0;i<loop/2;i++){
                counter2.incr();
            }
        });
        thread1.start();
        thread2.start();
        Thread.sleep(1000);

        System.out.println("多线程:"+counter2.getSum());
    }
}
