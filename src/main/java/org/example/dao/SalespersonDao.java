package org.example.dao;

import org.example.entity.Salesperson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * StoreManagementSystem
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 22:34
 * @since JDK17
 */

public class SalespersonDao {
    public Salesperson login(Connection con, Salesperson salesperson) throws SQLException {
        Salesperson curPerson = null;
        String sql = "SELECT * FROM salesperson where workNumber = ? and name = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, salesperson.getWorkNumber());
        preparedStatement.setString(2, salesperson.getName());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            curPerson = new Salesperson();
            curPerson.setId(resultSet.getInt("id"));
            curPerson.setWorkNumber(resultSet.getString("workNumber"));
            curPerson.setName(resultSet.getString("name"));
        }
        return curPerson;



    }
}
