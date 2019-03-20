package com.gysoft.bean.file;

import java.io.Serializable;

import lombok.Data;

/** 
 * 文件
 * @author DJZ-PJJ
 * @date 2018年5月29日 上午9:34:26 
 */
@SuppressWarnings("serial")
@Data
public class GyFileInfo implements Serializable {
	/**
	 * 文件uuid
	 */
	private String fileUuid;
	/**
	 * 文件md5
	 */
	private String fileMd5;
	/**
	 * 文件size
	 */
	private Integer fileSize;
}
