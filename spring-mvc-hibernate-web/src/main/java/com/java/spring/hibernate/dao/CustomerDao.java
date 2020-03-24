package com.java.spring.hibernate.dao;

import com.java.spring.hibernate.exceptions.LowBalance;
import com.java.spring.hibernate.model.Branch;
import com.java.spring.hibernate.model.Customer;
import com.java.spring.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CustomerDao {
    public String addCustomer(Customer customer , String branchCode){
        Session session = getSession();
        session.beginTransaction();

        Branch branch = (Branch) session.get( Branch.class, branchCode);
        if(branch == null) {
            return "There is no branch with this  branch code " + branchCode;
        }
        customer.setAccountID(branchCode);
        customer.setBranch(branch);
        session.persist(customer);
        session.getTransaction().commit();
        session.close();
        return "Customer has been added successfully : " + customer;
    }

    public String removeCustomer(String id){
        Session session = getSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class,id);

        if(customer != null) {
            session.delete(customer);
            session.getTransaction().commit();
            session.close();
            return "Customer with id : " + id + " has been successfully deleted ";
        }
        session.close();
        return "Error !!!  404 No Customer found with this Id !!!";
    }

    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public String creditBalance(int id, double balance){
        Session session = getSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class,id);
        customer.setBalance(customer.getBalance()+balance);
        session.update(customer);
        session.getTransaction().commit();
        session.close();
        return "Balance has been credited : " +customer;
    }

    public String debitBalance(int id, double balance){
        Session session = getSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class,id);
        if(customer == null) {
            session.close();
            return "Customer with id : " + id + " does not exists ";
        }
        if(customer.getBalance() < balance) {
            throw new LowBalance("!!! Not Enough balance in account ");
        }
        customer.setBalance(customer.getBalance()- balance);
        session.update(customer);
        session.getTransaction().commit();
        session.close();
        return "Balance has been credited : " +customer;
    }
    public List<Customer> showCustomers(){
        Session session = getSession();
        session.beginTransaction();
        List<Customer> customers= session.createQuery("from Customer ").list();
        session.getTransaction().commit();
        session.close();
        return customers;
    }
    public String updateCustomer(int id, Customer customer) {
        Session session = getSession();
        session.beginTransaction();
        Customer oldCustomer = (Customer) session.get(Customer.class,id);
        oldCustomer.setBalance(customer.getBalance());
        oldCustomer.setName(customer.getName());
        session.update(customer);
        session.getTransaction().commit();
        session.close();
        return "Customer  has been updated : " +oldCustomer;
    }
}
