package com.gysoft.bean.file;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 谷隐附件基础类
 * @author 周宁
 * @Date 2018-05-28 9:49
 */
@SuppressWarnings("serial")
@Data
public class GyAttachment implements Serializable {
    /**
     * 附件Uuid
     */
    @ApiModelProperty("附件Uuid")
    private String attachUuid;
    /**
     * 附件名称
     */
    @ApiModelProperty("附件名称")
    private String attachName;
    /**
     * 附件MD5
     */
    @ApiModelProperty("附件MD5")
    private String attachMd5;
    /**
     * 附件大小
     */
    @ApiModelProperty("附件大小")
    private Long attachSize;
}
