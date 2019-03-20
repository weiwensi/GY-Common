package com.gysoft.utils.test;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;

import com.gysoft.utils.jdbc.sqlhelp.CustomSQLExec;

public class SqlExecTest {
	
	
	public static void main(String[] args) {
        SQLExec sqlExec = new CustomSQLExec();
        // 设置数据库参数
        sqlExec.setDriver("com.mysql.jdbc.Driver");
        sqlExec.setUrl("jdbc:mysql://192.168.1.231:3306/dcp_test?useSSL=false&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true");
        sqlExec.setUserid("gyadmin");
        sqlExec.setPassword("Gy(!Djz!159");
        sqlExec.setKeepformat(true);
        sqlExec.setExpandProperties(false);
        // 要执行的脚本
        sqlExec.setSrc(new File("C:\\Users\\Administrator\\Desktop\\sql2.sql"));
        // 有出错的语句该如何处理
        sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
        sqlExec.setPrint(true); // 设置是否输出
        // 输出到文件 sql.out 中；不设置该属性，默认输出到控制台
//        sqlExec.setOutput(new File("src/sql.out"));
        sqlExec.setProject(new Project()); // 要指定这个属性，不然会出错
        sqlExec.execute();
    }
}
