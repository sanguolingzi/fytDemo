package fyt.business.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fyt.business.core.manager.jedis.JedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

@RestController
@RequestMapping("jedis")
public class jedisController {

    @Autowired
    private JedisManager jedisManager;

    //@Autowired
    //private RedisTemplate redisTemplate;


    @RequestMapping(value="setJson.do")
    public String  testSet() throws Exception{

        Map<String,Object> m = new HashMap<>();

        m.put("name","张三");
        m.put("age",1);
        m.put("date",new Date());

        ObjectMapper objectMapper = new ObjectMapper();
        jedisManager.getJedis().set("user-z", objectMapper.writeValueAsString(m));
        return "success";
    }

    @RequestMapping(value="getJson.do")
    public String  testGet(String key) throws Exception{
        return jedisManager.getJedis().get(key);
    }
}
