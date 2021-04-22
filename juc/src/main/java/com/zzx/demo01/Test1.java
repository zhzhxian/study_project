package com.zzx.demo01;

import java.util.concurrent.TimeUnit;

public class Test1 {
    public static void main(String[] args) throws Exception {
        //获取CPU的核数
        //CUP密集型  IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());
        //线程状态枚举值
        Thread.State state;
        //线程睡眠时间
        TimeUnit.SECONDS.sleep(2);
    }
}
