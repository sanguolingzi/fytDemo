package fyt.business.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;



public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;


    public boolean setValue(String key,String value){

        redisTemplate.opsForValue().set(key,value);
        return true;
    }

}
