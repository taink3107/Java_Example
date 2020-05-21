package org.example;

import javax.persistence.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    int id;
    Customer customer;
    List<ProductDetail> productDetails;
    @Column(name = "tong_tien",nullable = true)
    private double total;
    public OrderDetail() {
    }

    public int getId() {
        return id;
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
        String result = String.format(content, customer.getName(), printOrder(getProductDetails()), getTotalPrice() + "");
        try (PrintWriter writer = new PrintWriter(out)) {
            writer.print(result);
        };
    }

    public double getTotalPrice() {
        Optional<Double> optional = productDetails.stream().map(productDetail -> productDetail.getPrice()).reduce((aDouble, aDouble2) -> aDouble + aDouble2);
        return optional.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail detail = (OrderDetail) o;
        return id == detail.id &&
                Objects.equals(customer, detail.customer) &&
                Objects.equals(productDetails, detail.productDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, productDetails);
    }

    public StringBuffer printOrder(List<ProductDetail> product) {
        StringBuffer stringBuffer = new StringBuffer();
        for (ProductDetail p : product) {
            stringBuffer.append(p.toString());
        }
        return stringBuffer;
    }
}
