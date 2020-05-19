package org.example;

public class ProductDetail {
    private Product product;
    private int quantity;

    public ProductDetail( Product product, int quantity) {

        this.product = product;
        this.quantity = quantity;
    }

    public ProductDetail() {
    }

    public double getPrice(){
        return product.getPrice()*quantity;
    }
    @Override
    public String toString() {
        String result = "\n" +
                "<tr><th scope=\"row\"> " + product.getId() + " </th><td>" + product.getName() + "</td><td>" + quantity + "</td><td>" + getPrice() + "</td></tr>";
        return result;
    }
}
