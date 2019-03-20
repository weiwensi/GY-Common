package com.gysoft.bean.msgpush;

import com.alibaba.fastjson.JSONObject;
import com.gysoft.bean.login.UserBasicInfo;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 客户端动态推送消息
 *
 * @author 周宁
 * @Date 2018-09-26 15:36
 */
public class ClientMsgCenterPushMsg implements BasePushMsg {

    private List<String> userNames;

    private String productNum;

    private List<MsgCenterPushMsg> msgCenterPushMsgs;

    private List<String> iosDeviceTokens;

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public List<MsgCenterPushMsg> getMsgCenterPushMsgs() {
        return msgCenterPushMsgs;
    }

    public void setMsgCenterPushMsgs(List<MsgCenterPushMsg> msgCenterPushMsgs) {
        this.msgCenterPushMsgs = msgCenterPushMsgs;
    }

    public void setIosDeviceTokens(List<String> iosDeviceTokens) {
        this.iosDeviceTokens = iosDeviceTokens;
    }

    @Override
    public List<String> getUserNames() throws Exception {
        return userNames;
    }

    @Override
    public String getProductNum() throws Exception {
        return productNum;
    }

    @Override
    public List<String> getIosDeviceTokens() throws Exception {
        return iosDeviceTokens;
    }

    /**
     * 动态推送消息
     */
    @Data
    @Builder
    public static class MsgCenterPushMsg {
        /**
         * 消息id,用于客户端判断已读未读
         */
        private String msgId;
        /**
         * 操作人
         */
        private String operator;
        /**
         * 操作人姓名
         */
        private String operatorRealName;
        /**
         * 操作人头像文件uuid
         */
        private String portraitUuid;
        /**
         * 动作
         */
        private String action;
        /**
         * 操作对象
         */
        private String subject;
        /**
         * 操作对象id
         */
        private String subjectId;
        /**
         * 操作时间
         */
        private String sendTime;
        /**
         * 消息类型
         */
        private Integer type;
        /**
         * 项目Id
         */
        private String projectId;
        /**
         * 项目名称
         */
        private String projectName;
        /**
         * 额外信息
         */
        private Map extra;
    }

    /**
     * 构建客户端动态推送消息实体
     *
     * @param saveMsgParams
     * @param userBasicInfo
     * @param projectName
     * @param msgReciver
     * @return ClientMsgCenterPushMsg
     * @throws Exception
     * @author 周宁
     * @version 1.0
     */
    public static ClientMsgCenterPushMsg buildClientMsgCenterPushMsg(List<SaveMsgParam> saveMsgParams, UserBasicInfo userBasicInfo, String projectName, MsgReciver msgReciver) {
        ClientMsgCenterPushMsg clientDynamicPushMsg = new ClientMsgCenterPushMsg();
        clientDynamicPushMsg.setProductNum(userBasicInfo.getProductNum());
        String operator = userBasicInfo.getUserName();
        //消息相关人
        List<String> userNames = msgReciver.getUserNames();
        //ios用户与token的map
        Map<String, List<String>> iosUserTokenMap = msgReciver.getIosUserTokenMap();
        //操作人不用收到消息通知
        userNames.remove(operator);
        clientDynamicPushMsg.setUserNames(userNames);
        //ios设备token
        clientDynamicPushMsg.setIosDeviceTokens(userNames.stream().flatMap(userName->Optional.ofNullable(iosUserTokenMap.get(userName)).orElseGet(ArrayList::new).stream())
        .filter(token -> !StringUtils.isEmpty(token)).collect(Collectors.toList()));
        List<MsgCenterPushMsg> msgCenterPushMsgs = saveMsgParams.stream()
                .map(saveMsgParam ->
                        MsgCenterPushMsg.builder().action(saveMsgParam.getAction()).sendTime(BasePushMsg.getPushTime())
                                .subject(saveMsgParam.getSubject()).subjectId(saveMsgParam.getSubjectId()).extra((Map) JSONObject.parse(saveMsgParam.getExtra()))
                                .portraitUuid(saveMsgParam.getPortraitUuid()).type(saveMsgParam.getMsgType().getKey()).operator(saveMsgParam.getOperator())
                                .operatorRealName(saveMsgParam.getOperatorRealName()).projectId(saveMsgParam.getProjectId()).projectName(projectName).msgId(saveMsgParam.getMsgId()).build()
                ).collect(Collectors.toList());
        clientDynamicPushMsg.setMsgCenterPushMsgs(msgCenterPushMsgs);
        return clientDynamicPushMsg;
    }
}
