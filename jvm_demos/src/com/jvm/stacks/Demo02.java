package com.jvm.stacks;

/**
 * 演示栈内存溢出java.lang.StackOverFlowError
 * -Xss256k
 */
public class Demo02 {
    public static int COUNT = 0;

    public static void main(String[] args) {
        try {
            method();
        } catch (Throwable e) {
            System.out.println(COUNT);
//            e.printStackTrace();
        }
    }

    public static void method() {
        COUNT++;
        method();
    }
}

