package com.jvm;

public class JavaVirtualMachineStacksDemo002 {
    public static void demo() {
        int x = 0;
        for (int i = 0; i < 5000; i++) {
            x++;
        }
        System.out.println(x);
    }
}
