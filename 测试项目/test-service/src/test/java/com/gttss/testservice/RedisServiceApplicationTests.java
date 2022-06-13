package com.gttss.testservice;

import com.gttss.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RedisServiceApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
    }

//    @Test
//    void testf3(){
//        RedisUtil redisUtil=new RedisUtil();
//        redisUtil.setRedisTemplate(redisTemplate);
//        redisUtil.set("name5","555");
//    }
//    @Test
//    void testf4(){
//        RedisUtil redisUtil=new RedisUtil();
//        redisUtil.setRedisTemplate(redisTemplate);
//        redisUtil.del("name5", RedisCacheKey.FIRSTBOOK.toString());
//    }

    @Test
    void testf4(){
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        BoundValueOperations keyTest1 = redisTemplate.boundValueOps("keyTest");
        BoundValueOperations keyTest2 = redisTemplate.boundValueOps("keyTest2".toString());
        stringRedisTemplate.opsForValue().set("keyTest3", "valueTest333");
        String keyTest = (String) keyTest1.get();
//        String keyTest = redisUtil.get("keyTest").toString();
        System.out.println(keyTest);
    }

}
