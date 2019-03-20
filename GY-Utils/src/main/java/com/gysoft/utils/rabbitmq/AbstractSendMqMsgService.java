package com.gysoft.utils.rabbitmq;


import com.google.gson.Gson;
import com.gysoft.rabbit.service.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 消息发送抽象类
 * @author 周宁
 * @Date 2018-04-27 14:06
 */
public abstract class AbstractSendMqMsgService<T> {

    private Logger logger = LoggerFactory.getLogger(AbstractSendMqMsgService.class);

    protected String exchangeName;

    protected String queueName;

    public AbstractSendMqMsgService(String exchangeName, String queueName) {
        this.exchangeName = exchangeName;
        this.queueName = queueName;
    }

    @Resource
    protected RabbitMqService rabbitMqService;

    /**
     * 发送消息
     * @author 周宁
     * @param t
     * @return
     * @throws
     * @version 1.0
     */
    public void sendMsg(T t){
        try {
            rabbitMqService.send(exchangeName,queueName,"",t);
        } catch (Exception e) {
            logger.error("发送消息到rabbitmq失败msg={},exchangeName={},queueName={}",new Gson().toJson(t),exchangeName,queueName, e);
        }
    }
    /**
     * 发送消息到指定routingKey上(其中routingKey用于区分相同消息格式的不同类型)</br>
     * 例如:日志消息格式相同，我们用routingKey去区分不同源的消息</br>
     * @author 周宁
     * @param t
     * @param routingKey
     * @return
     * @throws Exception
     * @version 1.0
     */
    public void sendMsg(T t,String routingKey) throws Exception {
        throw new Exception("请实现自己的，，，");
    }
}
