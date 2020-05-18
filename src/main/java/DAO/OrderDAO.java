package DAO;

import entity.Order;
import entity.Product;
import jdbc.JDBC;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OrderDAO {

    public int addOrderReturnId(Order order) {
        String query = "INSERT INTO `order`(Quantity,Price,Create_date) VALUES (?,?,?);";
        try (Connection con = JDBC.getConnection();
             PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, order.getProduct().size());
               ps.setObject(2, order.getTotal_price1Order());
                LocalDateTime time = LocalDateTime.now();
                ps.setObject(3, time);
                int isCheck = ps.executeUpdate();
                if (isCheck > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    return rs.getInt(1);
                }
            }
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
    public boolean insertOrderDetail(Order order,int orderID){
        String sql = "INSERT INTO `order_detail`(IDcus,IDorder,Quantity,Price,Total_Price) VALUES (?,?,?,?,?);";
        int[] value = null;
        try (Connection con = JDBC.getConnection();
             PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;) {
            if (ps != null) {
                for (Product product : order.getProduct()){
                    ps.setObject(1, order.getCustomer().getId());
                    ps.setObject(2, orderID);
                    ps.setObject(3, product.getQuantity());
                    ps.setObject(4, product.getPrice());
                    ps.setObject(5, product.getQuantity()*product.getPrice());
                    ps.addBatch();
                }
               value = ps.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        for (int temp : value){
            if(temp < 0){
                return false;
            }
        }
        return true;
    }
}
