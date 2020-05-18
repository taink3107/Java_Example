package entity;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int status;

    public Product(int id, String name, int quantity, double price, int status) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return String.format("%s%18s%20s%17s",id,name,price,quantity);
    }
}
