package controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import entity.Customer;
import entity.Order;
import entity.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class Controller {

    FileHTML fileHTML = new FileHTML();
    JSONObject jsonDetail = new JSONObject();
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
            //json
           // pushToJSON(o.getProduct());
            File fileX = fileHTML.createFile(new File("Template.html"));
            StringBuffer buffer = printOrder(o.getProduct());
            fileHTML.replaceContent(buffer,fileX);

            System.out.println("Checkout sucess!");
        }

    }
    public StringBuffer printOrder(List<Product> product){
        StringBuffer stringBuffer = new StringBuffer();
        for (Product p : product){
            stringBuffer.append(printProduct(p));
        }
        return stringBuffer;
    }
    public String printProduct(Product product){
        String result = "\n" +
                "<tr><th scope=\"row\">"+product.getId() +"</th><td>"+product.getName()+"</td><td>"+product.getQuantity()+"</td><td>"+product.getPrice()+"</td></tr>";
        return result;
    }
//    public void pushToJSON(List<Product> products){
//        JSONArray jsonArray = new JSONArray();
//        int i =1;
//        for(Product product : products){
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("id",i++);
//            jsonObject.put("name",product.getName());
//            jsonObject.put("quantity",product.getQuantity());
//            jsonObject.put("price",product.getQuantity()*product.getPrice());
//            jsonArray.put(jsonObject);
//        }
//        jsonDetail.put("checkout",jsonArray);
//        System.out.println("value is: "+ jsonDetail);
//    }

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
