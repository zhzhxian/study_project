package com.jvm;

/**
 * -Xss256k
 */
public class JavaVirtualMachineStacksDemo004 {
    private static int a = 0;

    public static void main(String[] args) {
        try {
            tesStackOver();
        } catch (Throwable e) {
            System.out.println(a);
            e.printStackTrace();
        }
    }

    public static void tesStackOver() {
        a++;
        tesStackOver();
    }
}
