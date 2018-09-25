package fyt.business.core.manager.rocketmq;

import com.alibaba.rocketmq.client.producer.*;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.Random;

/**
 * 事务消息
 */
public class RocketMqTransProducer {
    private TransactionMQProducer producer;
    private String namesrvAddr;
    private String transGroup;
    public String topic;
    public String tags;
    private Random rand = new Random();

    public void init() throws Exception{
        producer = new TransactionMQProducer(transGroup);
        //在本地搭建好broker后,记得指定nameServer的地址
        producer.setNamesrvAddr(namesrvAddr);

        // 事务回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        // 事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(2);
        // 队列数
        producer.setCheckRequestHoldMax(2000);

        //设置发送到mq失败 重试次数
        producer.setRetryTimesWhenSendFailed(10);


        producer.setTransactionCheckListener(new TransactionCheckListener() {
            @Override
            public LocalTransactionState checkLocalTransactionState(MessageExt messageExt) {
                System.out.println("-------here----------");
                int seed = rand.nextInt(10);
                if(seed%2==0){
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        });

        producer.start();
        //final Semaphore semaphore = new Semaphore(0);

        for (int i = 0; i < 5; i++) {
            try {

                Message msgToBroker = new Message(topic, new String("事务消息"+i).getBytes());
                TransactionSendResult transactionSendResult =  producer.sendMessageInTransaction(msgToBroker, new LocalTransactionExecuter() {
                    @Override
                    public LocalTransactionState executeLocalTransactionBranch(Message message, Object o) {
                        //执行本地事务 执行成功 则 确认消息 否则回滚
                        System.out.println("----executeLocalTransactionBranch--------"+message+",,,,,,o:"+o);
                        int seed = rand.nextInt(10);
                        if(seed%2==0){
                            return LocalTransactionState.COMMIT_MESSAGE;
                        }
                        return LocalTransactionState.ROLLBACK_MESSAGE;
                    }
                },"abc");

                System.out.println(transactionSendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
    }


    public void destroy() {
        producer.shutdown();
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
