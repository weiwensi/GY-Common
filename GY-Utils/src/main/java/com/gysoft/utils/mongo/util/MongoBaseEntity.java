package com.gysoft.utils.mongo.util;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/**
 * 
* @Description: 实体父类 
* @author DJZ-PJJ
* @date 2018年3月29日 下午5:01:57
 */
@Data
@Document
public class MongoBaseEntity {

	@Id
	private String id;
	@Indexed
	@Field("epid")
	private String epid;
	@Field("ct")
	private Date createTime;
	@Field("cu")
	private String createUser;
	@Field("ut")
	private Date updateTime;
	@Field("uu")
	private String updateUser;
	
}
