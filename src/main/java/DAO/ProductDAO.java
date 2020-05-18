package DAO;

import entity.Product;
import jdbc.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM Product";
        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");
                double price = resultSet.getDouble("Price");
                int status = resultSet.getInt("Status");
                Product product = new Product(id, name, quantity, price, status);
                products.add(product);
            }
            return products;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
