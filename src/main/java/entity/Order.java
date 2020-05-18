package entity;

import java.util.List;
import java.util.Optional;

public class Order {
    private int id;
    private Customer customer;
    private List<Product> product;
    private double total_price1Order;

    public double getTotal_price1Order() {
        Optional<Double> result =  product.stream().map(product1 -> product1.getPrice()*product1.getQuantity()).reduce((o1,o2) -> o1 + o2 );
        return  result.get();
    }

    public void setTotal_price1Order(double total_price1Order) {
        this.total_price1Order = total_price1Order;
    }

    public Order(Customer customer, List<Product> product) {
        this.customer = customer;
        this.product = product;
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

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
