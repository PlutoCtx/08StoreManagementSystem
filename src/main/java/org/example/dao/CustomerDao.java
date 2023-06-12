package org.example.dao;

import org.example.entity.Customer;
import org.example.utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * StoreManagementSystem
 * 顾客处理
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 10:05
 * @since JDK17
 */

public class CustomerDao {

    /**
     * 添加
     * @param con
     * @param customer
     * @return
     * @throws SQLException
     */
    public int add(Connection con, Customer customer) throws SQLException {
        String sql = "INSERT INTO customer VALUE(null, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, customer.getVIPNumber());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getPhone());
        return preparedStatement.executeUpdate();
    }

    public int delete(Connection con, String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, id);
        return preparedStatement.executeUpdate();

    }

    public ResultSet list(Connection con, Customer bookType) throws SQLException {
        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM customer");
        if(StringUtil.isNotEmpty(bookType.getName())){
            stringBuffer.append(" and name like '%").append(bookType.getName()).append("%'");
        }
        PreparedStatement preparedStatement = con.prepareStatement(stringBuffer.toString().replaceFirst("and", "where"));
        return preparedStatement.executeQuery();
    }

    public int update(Connection con, Customer customer) throws SQLException {
        Logger.getGlobal().info("customer.getId()" + customer.getId() +
        "customer.getName()" + customer.getName() +
        "customer.getPhone()" + customer.getPhone());
        String sql = "UPDATE customer SET name = ?, phone = ? WHERE id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getPhone());
        preparedStatement.setInt(3, customer.getId());
        return preparedStatement.executeUpdate();
    }
}
