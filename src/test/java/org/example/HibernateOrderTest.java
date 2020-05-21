package org.example;

import exeption.ProductException;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ProductService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HibernateOrderTest {
    @Test
    public void itShouldGetSessionNotNull() {
        assertThat(HibernateUtil.getSession()).isNotNull();
    }

    @Test
    public void itShouldSaveProductAndReturnThisName() {
        Session session = HibernateUtil.getSession();
        ProductService service = new ProductService();

        Product product = new Product("Mang Cau", 100);
        Product productNew = service.saveProductService(product);
        assertThat(productNew.getName()).isEqualTo(product.getName());
    }

    @Test
    public void itShouldSaveFailAndThrowProductException() {
        Session session = HibernateUtil.getSession();
        ProductService service = new ProductService();
        Product product = new Product();
        Exception exception = assertThrows(ProductException.class,() ->
        service.saveProductService(product));
        assertThat(exception.getMessage()).contains("Cannot found data!");
    }
    @Test
    public void itShouldGetAllRowInProductTable(){
        ProductService service = new ProductService();
        List<Product> products = service.getAllProduct();
        assertThat(products.size()).isGreaterThan(0);
    }
}
