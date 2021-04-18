package com.zzx;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        //1、new Jedis对象
        Jedis jedis = new Jedis("localhost", 6379);
        // jedis所有的方法（命令）对应redis本身的指令
        //密码验证
        jedis.auth("123456");
        System.out.println(jedis.ping());
    }
}
