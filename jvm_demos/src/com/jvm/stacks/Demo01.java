package com.jvm.stacks;

/**
 * 演示栈帧
 */
public class Demo01 {
    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        method2(1,6);
    }

    private static int method2(int a, int b) {
        int c = a + b;
        return c;
    }
}
