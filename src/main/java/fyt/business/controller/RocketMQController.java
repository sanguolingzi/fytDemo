package fyt.business.controller;


import fyt.business.core.manager.rocketmq.user.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rocketmq")
public class RocketMQController {

    @Autowired(required = false)
    private UserProducer userProducer;


    @RequestMapping(value="sendMsg.do")
    public String test(){
        userProducer.sendMessage("测试消息！");
        return "success";
    }
}
