package org.example.dao;

import org.example.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
