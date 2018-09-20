package fyt.business.core.manager.rocketmq.user;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import fyt.business.core.manager.rocketmq.RocketMqProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserProducer extends RocketMqProducer{
    private final Logger logger = LoggerFactory.getLogger(UserProducer.class);

    public void sendMessage(String text) {
        Message msg = new Message(this.topic,this.tags, text.getBytes());
        SendResult sendResult = null;
        try {
            sendResult = this.defaultMQProducer.send(msg);
            System.out.println("Rule MSG_ID:"+sendResult.getMsgId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage() + String.valueOf(sendResult));
        }
        // 当消息发送失败时如何处理
        if (sendResult == null) {
            // TODO
            System.out.println("-----发送失败--------");
        }
    }
}
