package service;

import exeption.ProductException;
import org.example.Product;
import org.example.ProductModel;
import org.example.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {
    Session session = HibernateUtil.getSession();
    ProductModel model = new ProductModel(session);
    public Product saveProductService(Product product){
        Optional<Product> optional = Optional.empty();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            transaction.begin();
            optional = Optional.of(model.save(product));
            transaction.commit();
        } catch (PersistenceException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return optional.orElseThrow(() -> new ProductException("Cannot found data!"));
    }

    public List<Product> getAllProduct() {
        Transaction transaction = null;

       Optional<List<Product>> products = Optional.empty();
        try{
            transaction = session.getTransaction();
            transaction.begin();
            products = Optional.of(model.fillAll());
            transaction.commit();
        }catch (HibernateException ex){
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return products.orElseThrow(() -> new ProductException("Cannot get list product from database"));
    }
}
