package controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import entity.Customer;
import entity.Order;
import entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class Controller {
    ProductDAO productDAO = new ProductDAO();
    List<Product> products = productDAO.getAllProduct();
    Customer customer = new Customer(1, "Taink", 21);
    public void displayProduct() {
        System.out.println("ID      |      Name      |      Price      |      Quantity");
        System.out.println("----------------------------------------------------------");
        products.stream().forEach(System.out::println);

    }

    public void buyProduct() {
        boolean isYes = true;
        List<Product> pickProduct = new ArrayList<>();
        while (isYes) {
            displayProduct();
            Product product = selectProduct().get();
            pickProduct.add(product);
            System.out.print("Do you want to continue(Y/N): ");
            String s = new Scanner(System.in).next();
            isYes = Valid.checkAnswer(s);
        }
        if (!isYes) {
            Order o = new Order(customer, pickProduct);
            OrderDAO orderDAO = new OrderDAO();
            int idOrder = orderDAO.addOrderReturnId(o);
            orderDAO.insertOrderDetail(o,idOrder);
            System.out.println("Checkout sucess!");
        }

    }

    public Optional<Product> selectProduct() {
        System.out.print("-> Enter id product: ");
        int idPro = Valid.checkOption(1, 7);
        Optional<Product> p = products.stream().filter(product -> product.getId() == idPro).findFirst();
        System.out.print("-> Enter quantity product: ");
        int quantity = Valid.checkOption(1, p.get().getQuantity());
        p.get().setQuantity(quantity);
        return p;
    }


}
