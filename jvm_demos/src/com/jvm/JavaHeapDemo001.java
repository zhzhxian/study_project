package com.jvm;

public class JavaHeapDemo001 {
    public static void main(String[] args) throws Exception {
        System.out.println("1...");
        Thread.sleep(30000);
        byte[] array = new byte[1024 * 1024 * 10];
        System.out.println("2...");
        Thread.sleep(30000);
        array = null;
        System.gc();
        System.out.println("3...");
        Thread.sleep(100000000000L);
    }
}
