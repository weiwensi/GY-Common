package com.gysoft.bean.msgpush;

import com.gysoft.bean.utils.BaseEnum;


public enum MsgType implements BaseEnum<Integer,String> {
    /**
     * 资料提醒
     */
    DOC_REMIND(0,"资料提醒"),
    /**
     * 协同消息
     */
    CO_NEWS(1,"协同消息");

    private Integer type;
    private String desc;

    MsgType(Integer type,String desc){
        this.type = type;
        this.desc = desc;
    }

    @Override
    public Integer getKey() {
        return type;
    }

    @Override
    public String getValue() {
        return desc;
    }

}
