package com.atguigu.juc.lockaangsyn;

import java.util.concurrent.TimeUnit;

/**
 * LSH到此一游
 */
public class sisuo {

    public static void main(String[] args) {

        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(()->{
            synchronized (o1){
                System.out.println("A线程拿到了o1，想拿o2");
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                synchronized (o2){
                    System.out.println("A线程拿到了o2");
                }
            }
        },"A").start();
        new Thread(()->{
            synchronized (o2){
                System.out.println("B线程拿到了o2，想拿o1");
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                synchronized (o1){
                    System.out.println("B线程拿到了o1");
                }
            }
        },"B").start();
    }
}
