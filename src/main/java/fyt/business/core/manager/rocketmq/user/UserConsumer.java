package fyt.business.core.manager.rocketmq.user;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import fyt.business.core.manager.rocketmq.RocketMqConsumer;

import java.util.List;

public class UserConsumer extends RocketMqConsumer{

    public void init()  throws InterruptedException, MQClientException {
        this.initData();

        // 设置为集群消费(区别于广播消费)
        this.defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);

        this.defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {

            // 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                for(MessageExt msg:msgs){
                    System.out.println("-----msg----"+msg.toString());
                    System.out.println("-----msg body----"+new String(msg.getBody()));
                }
                // 如果没有return success ，consumer会重新消费该消息，直到return success
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
        defaultMQPushConsumer.start();
    }

}
