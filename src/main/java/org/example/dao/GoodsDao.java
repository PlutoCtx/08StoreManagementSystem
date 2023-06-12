package org.example.dao;

import org.example.entity.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * StoreManagementSystem
 * 货物处理
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 19:29
 * @since JDK17
 */

public class GoodsDao {
    public int add(Connection con, Goods goods) throws SQLException {
        String sql = "INSERT INTO goods VALUES (null, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, goods.getStoreNumber());
        preparedStatement.setString(2, goods.getStoreName());
        preparedStatement.setString(3, goods.getModel());
        preparedStatement.setDouble(4, goods.getPrice());
        return preparedStatement.executeUpdate();

    }

    public static int update(Connection conn, Goods goods) throws SQLException {
        String sql="UPDATE goods SET storeNumber = ?, storeName = ?, model = ?, price = ? WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, goods.getStoreNumber());
        preparedStatement.setString(2, goods.getStoreName());
        preparedStatement.setString(3, goods.getModel());
        preparedStatement.setDouble(4, goods.getPrice());
        preparedStatement.setInt(5, goods.getId());
        return preparedStatement.executeUpdate();


    }
}
