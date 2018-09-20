package fyt.business.core.manager.rocketmq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RocketMqProducer {

    private final Logger logger = LoggerFactory.getLogger(RocketMqProducer.class);

    private DefaultMQProducer defaultMQProducer;
    private String producerGroup;
    private String namesrvAddr;

    /**
     * Spring bean init-method
     */
    public void init() throws MQClientException {
        // 参数信息
        logger.info("DefaultMQProducer initialize!");
        logger.info(producerGroup);
        logger.info(namesrvAddr);

        // 初始化
        defaultMQProducer = new DefaultMQProducer(producerGroup);
        defaultMQProducer.setNamesrvAddr(namesrvAddr);
        defaultMQProducer.setInstanceName(String.valueOf(System.currentTimeMillis()));

        defaultMQProducer.start();

        logger.info("DefaultMQProudcer start success!");

    }

    /**
     * Spring bean destroy-method
     */
    public void destroy() {
        defaultMQProducer.shutdown();
    }

    public DefaultMQProducer getDefaultMQProducer() {
        return defaultMQProducer;
    }

    // ---------------setter -----------------

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void sendMessage( String text) {
        Message msg = new Message("MyTopic","MyTag", text.getBytes());
        SendResult sendResult = null;
        try {
            sendResult = defaultMQProducer.send(msg);
            System.out.println("Rule MSG_ID:"+sendResult.getMsgId());
            logger.info("Rule MSG_ID:"+sendResult.getMsgId());
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


    public static void main(String[] args) throws Exception{
        DefaultMQProducer  defaultMQProducer = new DefaultMQProducer("MyProducerGroup");
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
        defaultMQProducer.setInstanceName(String.valueOf(System.currentTimeMillis()));

        defaultMQProducer.start();

        Message msg = new Message("MyTopic","MyTags", "this is a new message".getBytes());
        defaultMQProducer.send(msg);
    }

}
