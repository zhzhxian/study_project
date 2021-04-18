package com.zzx;

import redis.clients.jedis.Jedis;

public class TestKey {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("123456");

        System.out.println("清空数据：" + jedis.flushDB());
        System.out.println("判断某个键是否存在：" + jedis.exists("username"));
        System.out.println("新增<'username','zhangsanfeng'>的键值对：" + jedis.set("username", "zhangsanfeng"));
        System.out.println("新增<'password','password'>的键值对：" + jedis.set("password", "password"));
        System.out.println("系统中所有的键如下：");
        System.out.println(jedis.keys("*"));

        System.out.println("删除键password：" + jedis.del("password"));
        System.out.println("判断键password是否存在：" + jedis.exists("password"));
        System.out.println("查看键username的存储类型：" + jedis.type("username"));
        System.out.println("随机返回key空间的一个：" + jedis.randomKey());
        System.out.println("重命名键：" + jedis.rename("username", "name"));
        System.out.println("取出修改后的name：" + jedis.get("name"));
        System.out.println("按索引切换数据库：" + jedis.select(0));
        System.out.println("删除当前选定数据库中的所有key：" + jedis.flushDB());
        System.out.println("返回当前数据库中key的数目：" + jedis.dbSize());
        System.out.println("删除所有数据库中的所有key" + jedis.flushAll());

    }
}
