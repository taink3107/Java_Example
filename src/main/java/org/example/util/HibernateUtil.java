package org.example.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory factory;
    static {
        try{
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }catch (Throwable ex){
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession(){
        return factory.openSession();
    }
}
