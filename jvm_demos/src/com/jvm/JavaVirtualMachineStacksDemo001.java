package com.jvm;

public class JavaVirtualMachineStacksDemo001 {
    public static void main(String[] args) {
        Object obj = null;
        int a = 9;
        int b = 10;
        method1(a, b);
        System.out.println(a);
    }

    private static void method1(int a, int b) {
        method2(a, b);
        System.out.println(b);
    }

    private static void method2(int a, int b) {
        int c = a + b;
        System.out.println(c);
    }

}
