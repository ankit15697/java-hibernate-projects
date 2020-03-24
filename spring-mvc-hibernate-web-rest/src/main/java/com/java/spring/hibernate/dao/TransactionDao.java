package com.java.spring.hibernate.dao;

import com.java.spring.hibernate.dummy.DummyData;
import com.java.spring.hibernate.model.Customer;
import com.java.spring.hibernate.util.HibernateUtil;
import org.hibernate.Session;

public class TransactionDao {
    public Customer debitBalance(DummyData dummyData){
        Session session = getSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class,dummyData.getId());
        customer.setBalance(customer.getBalance()- dummyData.getBalance());
        session.update(customer);
        session.getTransaction().commit();
        session.close();
       return customer;
    }

    public Customer creditBalance(DummyData dummyData){
        Session session = getSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class, dummyData.getId());
        customer.setBalance(customer.getBalance() + dummyData.getBalance());
        session.update(customer);
        session.getTransaction().commit();
        session.close();
        return customer;
    }
    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
