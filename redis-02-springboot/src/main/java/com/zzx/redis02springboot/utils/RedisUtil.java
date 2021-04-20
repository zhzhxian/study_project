package com.zzx.redis02springboot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存过期时间
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expaire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("expaire exceotion:{}", e);
            return false;
        }
    }

    /**
     * 获取过期时间
     *
     * @param key 不能为空
     * @return 时间（秒） 返回0代表永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("hashKey exception：{}", e);
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param keys 一个或者多个key
     */
    public void delete(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(Arrays.asList(keys));
            }
        }
    }

    /**
     * 获取普通缓存
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通对象存入redis
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("set exception：{}", e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间（秒） time要大于0，如果time小于或等于0将设置无限期
     * @return true 成功  false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("set with expaire exception:{}", e);
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几（大于0）
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("the delta must greater than 0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要减少几（大于0）
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("the delta must greater than 0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * HashSet
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功  false 失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("hset exception:{}", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据，如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间（秒） 注意：如果已经存在的hash表有时间，这里将会替换原有的时间
     * @return true 成功  false 失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expaire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("hset with time exception:{}", e);
            return false;
        }
    }

    /**
     * 获取hashKey对应的所有值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 多个键值
     * @return
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("hmset exception:{}", e);
            return false;
        }
    }

    /**
     * HashSet
     *
     * @param key  键
     * @param map  对应多个值
     * @param time 有效时间（秒）
     * @return
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expaire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("hmset with expaire exception:{}", e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以是多个 不能为空
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有改项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false 不存在
     */
    public boolean hHashKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash 递曾
     *
     * @param key  键
     * @param item 项
     * @param by   要增加的值（大于0）
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash 递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少的值（大于0）
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }
}
