package com.gysoft.utils.jdbc.sqlhelp;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.sql.SQLException;

/**
 * 自定义sql执行器
 *
 * @author 周宁
 * @Date 2018-08-10 15:13
 */
public class CustomSQLExec extends SQLExec {

    private static final Logger logger = LoggerFactory.getLogger(CustomSQLExec.class);

    @Override
    protected void execSQL(String sql, PrintStream out) throws SQLException {
        if (StringUtils.strip(sql.toUpperCase()).startsWith("DELETE")
            || StringUtils.strip(sql.toUpperCase()).startsWith("DROP")
            || StringUtils.strip(sql.toUpperCase()).startsWith("TRUNCATE")) {
            logger.error("CustomSQLExec.execSQL={}",sql);
            return;
        } else {
            super.execSQL(sql, out);
        }
    }
}
