package fyt.business.core.manager.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 事务消息
 */
public class RocketMqTransConsumer {
    public DefaultMQPushConsumer defaultMQPushConsumer;
    private String namesrvAddr;
    private String transGroup;
    public String topic;
    public String tags;

    public void initData() throws Exception{
        defaultMQPushConsumer = new DefaultMQPushConsumer(transGroup);
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //在本地搭建好broker后,记得指定nameServer的地址
        defaultMQPushConsumer.setNamesrvAddr(namesrvAddr);
        defaultMQPushConsumer.subscribe(topic, tags);
        //设置消费一次抓取多少消息
        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(10);
        //广播模式
        //consumer.setMessageModel(MessageModel.BROADCASTING);

        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.println("here start "+Thread.currentThread().getName());
                for (MessageExt msg : msgs) {
                    System.out.println(msg.toString());
                    System.out.println(new String(msg.getBody()));
                }
                System.out.println("here end "+Thread.currentThread().getName());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        defaultMQPushConsumer.start();

        //System.out.println("OrderConsumer Started.");
    }

    public void destroy() {
        defaultMQPushConsumer.shutdown();
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setTransGroup(String transGroup) {
        this.transGroup = transGroup;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
