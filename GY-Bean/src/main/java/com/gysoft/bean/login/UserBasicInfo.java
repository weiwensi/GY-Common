package com.gysoft.bean.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户基础信息，用于记录操作日志
 * @author 周宁
 * @Date 2018-06-20 9:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicInfo implements Serializable {
    /**
     * 操作人
     */
    private String userName;
    /**
     * 企业id
     */
    private String epid;
    /**
     * 操作人ip地址
     */
    private String  ip;
    /**
     * 当前时间戳
     */
    private Long currentMills;
    /**
     * 产品编号
     */
    private String productNum;
}
