package DAO;

import org.example.JDBC;
import org.example.OrderDetail;
import org.example.ProductDetail;

import javax.persistence.Entity;
import java.sql.*;
import java.time.LocalDateTime;

public class OrderDetailDAO {
    public int addOrderReturnId(OrderDetail order) {
        String query = "INSERT INTO `order`(Quantity,Price,Create_date,IDcus) VALUES (?,?,?,?);";
        try (Connection con = JDBC.getConnection();
             PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            //con.setAutoCommit(false);
            if (ps != null) {
                ps.setObject(1, order.getProductDetails().size());
                ps.setObject(2, order.getTotalPrice());
                LocalDateTime time = LocalDateTime.now();
                ps.setObject(3, time);
                ps.setObject(4, order.getCustomer().getId());
                int isCheck = ps.executeUpdate();
                if (isCheck > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    return rs.getInt(1);
                }
            }
           // con.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public boolean insertOrderDetail(OrderDetail order, int orderID) {
        String sql = "INSERT INTO `order_detail`(IDorder,Quantity,Price,Total_Price,IDproduct) VALUES (?,?,?,?,?);";
        if(orderID <= 0){
            throw new NullPointerException("Cannot found orderID");
        }
        if(order.getProductDetails() == null){
            throw new NullPointerException("Cannot found List order of customer");
        }
        int[] value = null;
        try (Connection con = JDBC.getConnection();
             PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;) {
              con.setAutoCommit(false);
            if (ps != null) {
                for (ProductDetail product : order.getProductDetails()) {
                    ps.setObject(1, orderID);
                    ps.setObject(2, product.getQuantity());
                    ps.setObject(3, product.getProduct().getPrice());
                    ps.setObject(4, product.getPrice());
                    ps.setObject(5, product.getProduct().getId());
                    ps.addBatch();
                }
                value = ps.executeBatch();
            }
             con.commit();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        for (int x : value) {
            if (x < 0) {
                return false;
            }
        }
        return true;
    }
}
