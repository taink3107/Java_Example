package exeption;

import org.hibernate.HibernateException;

public class ProductException extends HibernateException {

    public ProductException(String message) {
        super(message);
    }
}
