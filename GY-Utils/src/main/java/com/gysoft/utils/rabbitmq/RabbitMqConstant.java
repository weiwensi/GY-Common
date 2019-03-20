package com.gysoft.utils.rabbitmq;

/**
 * 系统所有涉及到的队列、交换机、routingKey配置
 *
 * @author 周宁
 * @Date 2018-04-28 13:47
 */
public interface RabbitMqConstant {

    /**
     * 资料转换交换机
     */
    String FILE_COVERT_EXHANGE = "file_covert_exchange";
    /**
     * 资料转换消息队列
     */
    String FILE_COVERT_QUEUE = "file_covert_queue";

    /**
     * client系统动态推送交换机
     */
    String CLIENT_MSG_EXCHANGE = "client_msg_exchange";
    /**
     * client系统动态推送队列
     */
    String CLIENT_MSG_QUEUE = "client_msg_queue";
    /**
     * 资料提醒routingKey
     */
    String CLIENT_DOC_REMIND_ROUTING_KEY = "client_doc_remind_routing_key";
    /**
     * 协同消息提醒routingkey
     */
    String CLIENT_CO_NEWS_ROUTING_KEY = "client_co_news_routing_key";
    /**
     * 踢掉mqttclient交换机
     */
    String KICK_MQTTCLIENT_EXCHANGE = "kick_mqttclient_exchange";
    /**
     * 踢掉mqttclient队列
     */
    String KICK_MQTTCLIENT_QUEUE = "kick_mqttclient_queue";

}
