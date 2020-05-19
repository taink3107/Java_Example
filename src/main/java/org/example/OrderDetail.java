package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class OrderDetail {
    int id;
    Customer customer;
    List<ProductDetail> productDetails;

    public OrderDetail(int id, Customer customer, List<ProductDetail> productDetails) {
        this.id = id;
        this.customer = customer;
        this.productDetails = productDetails;
    }

    public OrderDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public void generate(OutputStream out) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("Template.html")));
        String result = String.format(content, printOrder(getProductDetails()), getTotalPrice() + "");
        try (PrintWriter writer = new PrintWriter(out)) {
            writer.print(result);
        }
        ;
    }

    public double getTotalPrice() {
        Optional<Double> optional = productDetails.stream().map(productDetail -> productDetail.getPrice()).reduce((aDouble, aDouble2) -> aDouble + aDouble2);
        return optional.get();
    }

    public StringBuffer printOrder(List<ProductDetail> product) {
        StringBuffer stringBuffer = new StringBuffer();
        for (ProductDetail p : product) {
            stringBuffer.append(p.toString());
        }
        return stringBuffer;
    }
}
