package com.zzx.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 真正的此案城开发，公司中的开发不会使直接使用Thread
 * 线程就是一个单独的资源类，没有任何的附属的操作
 * 属性、方法
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) throws Exception {
        //并发：多个线程操作同一个资源类，把资源类丢入线程
        Ticket2 ticket2 = new Ticket2();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket2.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket2.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket2.sale();
            }
        }, "C").start();
    }
}

/**
 * 资源类
 */
class Ticket2 {
    //属性、方法
    private int number = 50;
    Lock lock = new ReentrantLock();

    public void sale() {
        //加锁
        lock.lock();
        try {
            //业务代码
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (number--) + "张票,剩余" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }
}