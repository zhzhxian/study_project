package com.jvm;

public class JavaVirtualMachineStacksDemo003 {
    public static void main(String[] args) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(4);
//        sb.append(5);
//        sb.append(6);
//        new Thread(() -> {
//            test(sb);
//        }).start();
//        sb.append(7);
//        System.out.println(sb);

        StringBuffer sb = new StringBuffer();
        sb.append(4);
        sb.append(5);
        sb.append(6);
        new Thread(() -> {
            test2(sb);
        }).start();
        sb.append(7);
        System.out.println(sb);
    }

    public static void test(StringBuilder sb) {
        sb.append(1);
        sb.append(2);
        sb.append(3);
        System.out.println(sb);
    }

    public static void test2(StringBuffer sb) {
        sb.append(1);
        sb.append(2);
        sb.append(3);
        System.out.println(sb);
    }
}
