package com.gysoft.bean.msgpush;

import lombok.Data;

import java.util.*;

/**
 * @author 周宁
 * @Date 2018-12-18 17:51
 */
@Data
public class MsgReciver {
    /**
     * 接收消息用户
     */
    private List<String> userNames;
    /**
     * ios用户与设备token的map
     */
    private Map<String, List<String>> iosUserTokenMap;

    public MsgReciver() {

    }

    public MsgReciver(Collection<String> userNames, Map<String, List<String>> iosUserTokenMap) {
        this.userNames = new ArrayList<>(userNames);
        this.iosUserTokenMap = iosUserTokenMap;
    }
}
