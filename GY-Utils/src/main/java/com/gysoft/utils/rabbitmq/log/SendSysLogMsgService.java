package com.gysoft.utils.rabbitmq.log;

import com.gysoft.bean.syslog.SaveSysLogParam;
import com.gysoft.bean.syslog.SysLogConstant;
import com.gysoft.rabbit.utils.StringUtils;
import com.gysoft.utils.rabbitmq.AbstractSendMqMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * @author 周宁
 * @Date 2018-04-27 14:24
 */
@Component
public class SendSysLogMsgService extends AbstractSendMqMsgService<Supplier<SaveSysLogParam>> {

    private Logger logger = LoggerFactory.getLogger(SendSysLogMsgService.class);

    public SendSysLogMsgService() {
        super(SysLogConstant.SYS_LOG_EXCHANGE,SysLogConstant.SYS_LOG_QUEUE);
    }

    @Override
    @Async("logSendExecutor")
    public void sendMsg(Supplier<SaveSysLogParam> supplier, String routingKey) {
        try {
            SaveSysLogParam saveSysLogParam = supplier.get();
            //不存在操作描述不记录日志
            if(StringUtils.isEmpty(saveSysLogParam.getOperDesc())){
                return;
            }
            rabbitMqService.send(this.exchangeName,this.queueName,routingKey,saveSysLogParam);
        } catch (Exception e) {
            logger.error("发送日志消息异常",e);
            //记录日志发送消息失败不要抛出异常影响接口的调用
        }
    }

}
