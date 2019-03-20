package com.gysoft.utils.test.threadlocal;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 周宁
 * @Date 2018-08-14 11:39
 */
public class ConnectionManager {

    private static Connection connect = null;

    /**
     * 打开connection连接
     *
     * @return Connection
     */
    public static Connection openConnection() {
        if (connect == null) {
            try {
                connect = (Connection) DriverManager.getConnection("");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }

    /**
     * 关闭连接
     */
    public static void closeConnection() {
        if (null != connect) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
