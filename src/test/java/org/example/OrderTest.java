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
        Customer customer = new Customer();
        List<ProductDetail> productDetailList = new ArrayList<>();
        OrderDetail orderDetail = customer.makeOrder(productDetailList);
        assertThat(orderDetail).isNotNull();
    }

    @Test
    public void shouldGenerateHTML() throws IOException {
        Customer customer = new Customer();
        List<ProductDetail> productDetailList = new ArrayList<>();

        OutputStream out = new FileOutputStream(new File("./orderHTML.html"));
        customer.makeOrder(productDetailList).generate(out);

        List<String> contents = Files.readAllLines(Paths.get("./orderHTML.html"));
        assertThat(contents).isNotNull();
        assertThat(contents.size()).isGreaterThan(0);
    }
}
