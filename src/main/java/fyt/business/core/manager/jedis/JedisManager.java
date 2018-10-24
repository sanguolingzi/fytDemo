package fyt.business.core.manager.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class JedisManager {

    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis(){

        return jedisPool.getResource();
    }


    public void test(){
        //string list set hset
        //标准的设置key value值 方法 此方法未设置生命周期 故永久存在
        //jedisPool.getResource().set("key","value");

        //设置 key value值 生命周期单位为second
        //jedisPool.getResource().setex("key","second","value");

        //nx not exist 若不存在 则设置 key value
        //jedisPool.getResource().setnx("key","value");

        //若 不存在则设置key value 同时该key 生命周期为 time long型
        //jedisPool.getResource().set("key","value","nx","ex","time")

        //判断是否存在某个key
        //jedisPool.getResource().exists("key");

        //查看某个key的生命周期 -2 不存在  -1 永久存在 0 集群中会返回 视为超时  >0 则代表剩余生命周期
        //jedisPool.getResource().ttl("key");


        //操作list
        //队列 左侧插入
        //jedisPool.getResource().lpush("key");
        //队列 左侧弹出
        //jedisPool.getResource().lpop("key");

                //队列右侧插入
        //jedisPool.getResource().rpush("key");
                //队列右侧弹出
        //jedisPool.getResource().rpop("key");

        //操作set
        //jedisPool.getResource().hset("","","");
        //jedisPool.getResource().hget("key","field");

        //操作hset

        //jedisPool.getResource().zadd("key","score","value");
    }





}
