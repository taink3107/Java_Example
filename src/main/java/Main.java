import DAO.ProductDAO;
import controller.Controller;
import controller.Valid;
import entity.Product;
import jdbc.JDBC;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        ProductDAO productDAO = new ProductDAO();
        List<Product> list = productDAO.getAllProduct();
        controller.buyProduct();

    }
}
