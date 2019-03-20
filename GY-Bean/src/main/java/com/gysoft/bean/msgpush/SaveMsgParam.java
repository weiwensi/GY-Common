package com.gysoft.bean.msgpush;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 周宁
 * @Date 2018-09-25 15:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveMsgParam {
    /**
     * 消息ID
     */
    private String msgId;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作人真实姓名
     */
    private String operatorRealName;
    /**
     * 操作动作
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
     * 操作人头像文件uuid
     */
    private String portraitUuid;
    /**
     * 项目id
     */
    private String projectId;
    /**
     * 消息类型
     */
    private MsgType msgType;
    /**
     * 收到该条动态的用户名称
     */
    private List<String> recivers;

    private String extra = "{}";
}
