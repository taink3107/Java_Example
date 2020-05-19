package org.example;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        List<ProductDetail> productDetailList = new ArrayList<>();
        ProductDetail productDetail = new ProductDetail(new Product(1, "Táo", 5000), 10);
        productDetailList.add(productDetail);

        OutputStream stream = new FileOutputStream(new File("Template.html"));

        customer.makeOrder(productDetailList).generate(stream);
        String contents = new String(Files.readAllBytes(Paths.get("Template.html")));
      //  assertThat(contents).isNotNull();
        assertThat(contents.length()).isGreaterThan(0);

    }
}
