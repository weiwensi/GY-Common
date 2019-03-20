package com.gysoft.bean.syslog;

import com.gysoft.bean.login.UserBasicInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 周宁
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveSysLogParam {

    /**
     * 企业id
     */
    private String epid;
    /**
     * 操作功能
     */
    private String operFunction;
    /**
     * 操作名称
     */
    private String operName;
    /**
     * 操作说明
     */
    private String operDesc;
    /**
     * 操作人ip
     */
    private String operIp;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作时间
     */
    private Long operTime;

    public SaveSysLogParam(UserBasicInfo userBasicInfo,String operFunction,String operName,String operDesc){
        this.epid = userBasicInfo.getEpid();
        this.operator = userBasicInfo.getUserName();
        this.operDesc = operDesc;
        this.operFunction = operFunction;
        this.operIp = userBasicInfo.getIp();
        this.operName = operName;
        this.operTime = userBasicInfo.getCurrentMills();
    }

}
