package org.example;

import org.example.util.HibernateUtil;
import org.hibernate.*;

import java.util.List;

public class ProductModel {
    Session session;

    public ProductModel(Session session) {
        this.session = session;
    }

    public Product save(Product product) {
        session.merge(product);
    return  product;
    }
    public List<Product> fillAll(){
        return session.createQuery("FROM Product ",Product.class).getResultList();
    }
}
