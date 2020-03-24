package com.java.spring.hibernate.dao;

import com.java.spring.hibernate.dummy.DummyTransaction;
import com.java.spring.hibernate.model.Customer;
import com.java.spring.hibernate.util.HibernateUtil;
import org.hibernate.Session;

public class TransactionDao {
    public Customer debitBalance(DummyTransaction dummyTransaction){
        Session session = getSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class, dummyTransaction.getId());
        customer.setBalance(customer.getBalance()- dummyTransaction.getBalance());
        session.update(customer);
        session.getTransaction().commit();
        session.close();
       return customer;
    }

    public Customer creditBalance(DummyTransaction dummyTransaction){
        Session session = getSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class, dummyTransaction.getId());
        customer.setBalance(customer.getBalance() + dummyTransaction.getBalance());
        session.update(customer);
        session.getTransaction().commit();
        session.close();
        return customer;
    }
    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
