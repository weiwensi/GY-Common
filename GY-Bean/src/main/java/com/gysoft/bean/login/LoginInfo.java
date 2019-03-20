package com.gysoft.bean.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 周宁
 * @Date 2018-06-05 10:39
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo implements Serializable {

    private String userName;

    private String epid;

    private String areaId;

    private String roleId;

    private String productNum;

    private List<String> authCodes;

    private String mqttClientId;
    /**
     * 用户登录ip
     */
    private String loginIp;
    /**
     * 用户登录时间
     */
    private Long longAt;
}
