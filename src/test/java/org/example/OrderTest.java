package org.example;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAO.OrderDetailDAO;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

public class OrderTest {

    @Test
    public void customerCanMakeOrder() {
        Customer customer = new Customer(1, "Taink", 21);
        List<ProductDetail> productDetailList = new ArrayList<>();
        OrderDetail orderDetail = customer.makeOrder(productDetailList);
        assertThat(orderDetail).isNotNull();
    }

    @Test
    public void shouldGenerateHTML() throws IOException {
        Customer customer = new Customer(1, "Taink", 21);
        List<ProductDetail> productDetailList = Arrays.asList(
                new ProductDetail(new Product(1, "Tu lanh", 1000), 2),
                new ProductDetail(new Product(2, "Tivi", 3000), 3)
        );

        OutputStream out = new FileOutputStream(new File("./orderHTML.html"));
        customer.makeOrder(productDetailList).generate(out);

        String contents = new String(Files.readAllBytes(Paths.get("./orderHTML.html")));
        assertThat(contents).contains("Tu lanh");
        assertThat(contents).contains("Tivi");
    }

    @Test
    public void fileShouldContainsTotal() throws IOException {
        Customer customer = new Customer(1, "Taink", 21);
        List<ProductDetail> productDetailList = Arrays.asList(
                new ProductDetail(new Product(1, "Laptop", 1000), 2),
                new ProductDetail(new Product(2, "Tivi", 3000), 3)
        );
        OutputStream out = new FileOutputStream(new File("./orderHTML.html"));
        customer.makeOrder(productDetailList).generate(out);
        String contents = new String(Files.readAllBytes(Paths.get("./orderHTML.html")));
        assertThat(contents).isNotNull();
        assertThat(contents).contains("Total");
        assertThat(contents).contains("11000");

    }

    @Test
    public void canConnectToDataBase() {
        Connection connection = JDBC.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    public void canUploadOrderToDatabaseAndReturnIDorder() {
        OrderDetailDAO dao = new OrderDetailDAO();
        Customer customer = new Customer(1, "Taink", 21);
        List<ProductDetail> productDetailList = Arrays.asList(
                new ProductDetail(new Product(1, "Laptop", 1000), 2),
                new ProductDetail(new Product(2, "Tivi", 3000), 3)
        );
        OrderDetail detail = customer.makeOrder(productDetailList);
        int index = dao.addOrderReturnId(detail);
        assertThat(index).isGreaterThan(0);
    }

    @Test
    public void canUploadOrderAndReturnIDorderThenAddOrderDetailToDatabase() {
        OrderDetailDAO dao = new OrderDetailDAO();
        Customer customer = new Customer(1, "Taink", 21);
        List<ProductDetail> productDetailList = Arrays.asList(
                new ProductDetail(new Product(1, "Táo", 1000), 2),
                new ProductDetail(new Product(2, "Lê", 3000), 3)
        );
        OrderDetail detail = customer.makeOrder(productDetailList);
        int index = dao.addOrderReturnId(detail); // Order
        dao.insertOrderDetail(detail, index); // OrderItem
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenInsertWithoutOrderID() {
        ////input;
        OrderDetailDAO dao = new OrderDetailDAO();
        int orderID = -1;
        Customer customer = new Customer(1, "Taink", 21);
        List<ProductDetail> productDetailList = Arrays.asList(
                new ProductDetail(new Product(1, "Laptop", 1000), 2),
                new ProductDetail(new Product(2, "Tivi", 3000), 3)
        );
        OrderDetail detail = customer.makeOrder(productDetailList);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            dao.insertOrderDetail(detail, orderID);
        });
        assertThat(exception.getMessage()).contains("Cannot found orderID");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenInsertWithoutListProduct() {
        ////input;
        OrderDetailDAO dao = new OrderDetailDAO();
        int orderID = 1;
        Customer customer = new Customer(1, "Taink", 21);
        List<ProductDetail> productDetailList = null;
        OrderDetail detail = customer.makeOrder(productDetailList);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            dao.insertOrderDetail(detail, orderID);
        });
        assertThat(exception.getMessage()).contains("Cannot found List order of customer");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenInsertWithoutListProductAndOrderID() {
        ////input;
        OrderDetailDAO dao = new OrderDetailDAO();
        int orderID = -1;
        Customer customer = new Customer(1, "Taink", 21);
        List<ProductDetail> productDetailList = null;
        OrderDetail detail = customer.makeOrder(productDetailList);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            dao.insertOrderDetail(detail, orderID);
        });
        assertThat(exception.getMessage()).contains("Cannot found orderID");
    }


}
