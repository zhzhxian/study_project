package com.zzx.demo01;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestArrayList {
    public static void main(String[] args) {

        // List<String> list = new ArrayList<>();//会报错
        // List<String> list = new Vector<>();
         List<String> list = Collections.synchronizedList(new ArrayList<>());
        //CopyOnWrite写入时复制
//        List<String> list = new CopyOnWriteArrayList<>();
        new HashMap();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(Thread.currentThread().getName() + "--->" + list);
            }, String.valueOf(i)).start();
        }
    }
}
