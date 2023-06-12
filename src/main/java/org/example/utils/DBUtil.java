package org.example.utils;

import java.sql.*;
import java.util.logging.Logger;

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
     * 数据库名
     */
    String databaseName = "";
    /**
     * sql语句
     */
    String SQL;

    /**
     * 查询到的记录的第一行，也就是指字段名
     */
    String [] columnName;
    /**
     * 查询到的所有记录
     */
    String [][] records;

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
    public void setDatabaseName(String s) {
        databaseName = s.trim();
    }

    public void setSQL(String SQL) {
        this.SQL = SQL.trim();
    }

    public String[] getColumnName() {
        if(columnName == null ){
            Logger.getGlobal().info("先查询记录");
            return new String[0];
        }
        return columnName;
    }

    public String[][] getRecord() {
        startQuery();
        return records;
    }
    private void startQuery() {
        Connection con;
        Statement sql;
        ResultSet rs;
        try {
            con = DriverManager.getConnection(url, username, password);
            sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = sql.executeQuery(SQL);
            ResultSetMetaData metaData = rs.getMetaData();
            // 字段数目，也就是列数
            int columnCount = metaData.getColumnCount();
            // 获取字段名
            columnName = new String[columnCount];
            for(int i = 1;i <= columnCount;i++){
                columnName[i-1] = metaData.getColumnName(i);
            }
            rs.last();
            // 结果集中的记录数目，recordAmount是记录数目
            int recordAmount = rs.getRow();
            records = new String[recordAmount][columnCount];
            int i = 0;
            rs.beforeFirst();
            while(rs.next()) {
                for(int j = 1; j <= columnCount; j++){
                    // 第i条记录，放入二维数组的第i行，也就是数组的第i-1号位置行
                    records[i][j-1] = rs.getString(j);
                }
                i++;
            }
            con.close();
        }catch(SQLException e) {
            Logger.getGlobal().info("请输入正确的表名"+e);
        }
    }
}
