package com.java.spring.hibernate.dao;


import com.java.spring.hibernate.model.Customer;
import com.java.spring.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CustomerDao {
    public List<Customer> showCustomers(){
        Session session = getSession();
        session.beginTransaction();
        List<Customer> customers= session.createQuery("from Customer ").list();
        session.getTransaction().commit();
        session.close();
        return customers;
    }
    public Customer addCustomer(Customer customer){
        Session session = getSession();
        session.beginTransaction();
        session.persist(customer);
        session.getTransaction().commit();
        session.close();
        return customer;
    }
    public Customer removeCustomer(int id) {
        Session session = getSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class,id);
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
        return customer;
    }
    public Customer updateCustomer(Customer customer) {
        Session session = getSession();
        session.beginTransaction();
        Customer oldCustomer = (Customer) session.get(Customer.class,customer.getId());
        oldCustomer.setBalance(customer.getBalance());
        oldCustomer.setName(customer.getName());
        session.update(oldCustomer);
        session.getTransaction().commit();
        session.close();
        return oldCustomer;
    }
    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
