package com.zzx.redis02springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzx.redis02springboot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
class Redis02SpringbootApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {

        //redisTemplate.opsForValue操作字符串
        //redisTemplate.opsForList操作list
        //redisTemplate.opsForSet操作Set
        //redisTemplate.opsForHash操作hash
        //redisTemplate.opsForZSet操作Zset
        //redisTemplate.opsForGeo()操作Geo
        //...

        //除了基本的操作常用的方法可以直接使用redisTemplate来操作，比如事务，基本的CRUD

        //获取redis的连接对象
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//		connection.flushAll();
        connection.flushDb();

        redisTemplate.opsForValue().set("mykey", "hello");
        String value = (String) redisTemplate.opsForValue().get("mykey");
        log.info("redis获取到的结果：{}", value
        );
    }

    @Test
    public void test() throws Exception {
        //清空数据库(正式项目中慎用)
        redisTemplate.getConnectionFactory().getConnection().flushDb();

        User user = new User("张三丰", 120);
        //序列化
        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user", jsonUser);

        log.info("user--->{}", redisTemplate.opsForValue().get("user"));
    }

}
