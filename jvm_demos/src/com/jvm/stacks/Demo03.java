package com.jvm.stacks;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示堆内存溢出 java.lang.OutOfMemoryError: Java heap space
 * -Xmx8m
 */
public class Demo03 {
    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String  s = "hello";
            while (true) {
                list.add(s);
                s +=s;
                i++;
            }
        }catch (Throwable e) {
            System.out.println(i);
            e.printStackTrace();
        }
    }
}
