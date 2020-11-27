package com.atguigu.juc;

import com.sun.jmx.snmp.tasks.ThreadService;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//创建票的资源类
class Ticket{

    //定义票数
    private int num = 30;

    //卖票的方法
    //回顾synchronized
    /*public synchronized void sale() {
        if (num > 0) {
            //Thread.currentThread().getName()获取当前线程名称
            System.out.println(Thread.currentThread().getName() + "卖出:" + (num--) + "，还剩" + num);
        }
    }*/
    //使用lock实现卖票
    public  void sale() {
        //创建可重入锁
        Lock lock = new ReentrantLock();
        //首先上锁
        lock.lock();
        try {

                if (num > 0) {
                    //Thread.currentThread().getName()获取当前线程名称
                    System.out.println(Thread.currentThread().getName() + "卖出:" + (num--) + "，还剩" + num);
                }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }
}
//3个售票员同时卖30张票
public class LockTest {

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(
                3,
                5,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            Ticket ticket = new Ticket();
            for (int i = 0; i < 3; i++) {
                executorService.execute(() -> {

                    for (int i1 = 0; i1 < 10; i1++) {
                        ticket.sale();
                    }
                });
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
