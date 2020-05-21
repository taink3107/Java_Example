package org.example;

import javax.persistence.Entity;
import java.util.Objects;

public class ProductDetail {
    private Product product;
    private int quantity;

    public ProductDetail( Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetail that = (ProductDetail) o;
        return quantity == that.quantity &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }

    public int getQuantity() {
        return quantity;
    }


    public double getPrice(){
        return product.getPrice()*quantity;
    }

}
