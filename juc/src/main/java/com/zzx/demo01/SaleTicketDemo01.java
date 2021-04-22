package com.zzx.demo01;

/**
 * 真正的此案城开发，公司中的开发不会使直接使用Thread
 * 线程就是一个单独的资源类，没有任何的附属的操作
 * 属性、方法
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) throws Exception {
        //并发：多个线程操作同一个资源类，把资源类丢入线程
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

/**
 * 资源类
 */
class Ticket {
    //属性、方法
    private int number = 50;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了第" + (number--) + "张票,剩余" + number);
        }
    }
}