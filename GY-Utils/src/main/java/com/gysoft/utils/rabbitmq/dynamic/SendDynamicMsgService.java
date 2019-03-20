package com.gysoft.utils.rabbitmq.dynamic;

import com.gysoft.bean.msgpush.ClientMsgCenterPushMsg;
import com.gysoft.utils.rabbitmq.AbstractSendMqMsgService;
import com.gysoft.utils.rabbitmq.RabbitMqConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;


/**
 * @author 周宁
 * @Date 2018-09-29 9:27
 */
@Component
public class SendDynamicMsgService extends AbstractSendMqMsgService<Supplier<ClientMsgCenterPushMsg>> {

    private Logger logger = LoggerFactory.getLogger(SendDynamicMsgService.class);

    public SendDynamicMsgService() {
        super(RabbitMqConstant.CLIENT_MSG_EXCHANGE, RabbitMqConstant.CLIENT_MSG_QUEUE);
    }

    @Async("dynamicSendExecutor")
    @Override
    public void sendMsg(Supplier<ClientMsgCenterPushMsg> supplier, String routingKey) {
        try {
            rabbitMqService.send(this.exchangeName, this.queueName, routingKey, supplier.get());
        } catch (Exception e) {
            logger.error("发送动态消息失败",e);
        }

    }
}
