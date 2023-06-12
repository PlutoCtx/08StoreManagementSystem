package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * StoreManagementSystem
 * 数据库连接
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 10:25
 * @since JDK17
 */

public class DBUtil {

    /**
     数据库
     */
    private String url = "jdbc:mysql://localhost:3306/08storeManagement";
    /**
     * 用户名
     */
    private String username = "root";
    /**
     * 密码
     */
    private String password = "Shangxiao111";
    /**
     * 驱动名称
     */
    private String jdbcName = "com.mysql.cj.jdbc.Driver";

    /**
     * 获取数据库连接
     * @return  返回连接
     * @throws Exception 没连上
     */
    public Connection getConnection() throws Exception{
        Class.forName(jdbcName);
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }


    /**
     * 关闭数据库连接
     * @param connection    数据库连接
     * @throws Exception    异常
     */
    public void closeConnection(Connection connection) throws Exception{
        if (connection != null){
            connection.close();
        }
    }
}
