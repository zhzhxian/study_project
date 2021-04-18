package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 调整堆内存大小
 * -Xmx8m
 */
public class JavaHeapOutofMemory {
    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<String>();
            String s = "hello";
            while (true) {
                list.add(s);
                s += s;
                i++;
            }
        } catch (Throwable e) {
            System.out.println(i);
            e.printStackTrace();
        }
    }
}
