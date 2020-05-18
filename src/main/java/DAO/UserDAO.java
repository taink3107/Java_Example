package DAO;

import entity.Customer;
import jdbc.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public List<Customer> getAll(int id_city) {
        List<Customer> personList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                int age = resultSet.getInt("age");
                Customer customer = new Customer(id,name,age);
                personList.add(customer);
            }
            return personList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
