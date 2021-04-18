package com.zzx;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * redis事务测试
 */
public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123456");
        //清空数据库
        jedis.flushDB();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","zhangsanfeng");
        jsonObject.put("age",120);
        //开启事务
        Transaction ts = jedis.multi();
        try {
            ts.set("user1",jsonObject.toJSONString());
            ts.set("user2",jsonObject.toJSONString());
            int a = 1/0;

            ts.exec();//执行事务
        } catch (Exception e) {
            //放弃事务
            ts.discard();

            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            //关闭连接
            jedis.close();
        }

    }
}
