package com.java.spring.hibernate.controller;

import com.java.spring.hibernate.dao.CustomerDao;
import com.java.spring.hibernate.dummy.DummyCustomer;
import com.java.spring.hibernate.dummy.DummyId;
import com.java.spring.hibernate.dummy.DummyUpdateCustomer;
import com.java.spring.hibernate.exceptions.InvalidBranch;
import com.java.spring.hibernate.exceptions.InvalidCustomer;
import com.java.spring.hibernate.model.Branch;
import com.java.spring.hibernate.model.Customer;
import com.java.spring.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller

// This class will be responsible for controlling the customer
@RequestMapping("/customer")
public class CustomerController {
     private CustomerDao customerDao;

    public CustomerController() {
        customerDao = new CustomerDao();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Customer> showCustomers() {
        return customerDao.showCustomers();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody Customer addCustomer(@RequestBody DummyCustomer dummyCustomer) {
        Session session = getSession();
        session.beginTransaction();
        String branchCode = dummyCustomer.getBranchCode();
        Branch branch = (Branch) session.get( Branch.class, branchCode);
        session.close();
        if(branch == null) {
            throw new InvalidBranch("No branch find with this branch code :: " + branchCode);
        }
        Customer customer = new Customer();
        customer.setBranch(branch);
        customer.setName(dummyCustomer.getName());
        customer.setBalance(dummyCustomer.getBalance());
        customer.setAccountID(dummyCustomer.getBranchCode());
        return customerDao.addCustomer(customer);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Customer removeCustomer(@RequestBody DummyId dummyId) {
        Session session = getSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get( Customer.class, dummyId.getId());
        session.close();
        if(customer == null) {
            throw new InvalidCustomer("No customer found with this id  :: " + dummyId.getId());
        }
        return customerDao.removeCustomer(dummyId.getId());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Customer updateCustomer(@RequestBody DummyUpdateCustomer dummyUpdateCustomer) {
        Session session = getSession();
        session.beginTransaction();
        String branchCode = dummyUpdateCustomer.getBranchCode();
        Branch branch = (Branch) session.get( Branch.class, branchCode);

        if(branch == null) {
            session.close();
            throw new InvalidBranch("No branch find with this branch code :: " + branchCode);
        }
        Customer customer = (Customer) session.get(Customer.class,dummyUpdateCustomer.getId());
        if(customer == null) {
            session.close();
            throw new InvalidCustomer("No customer with this id is present : " + dummyUpdateCustomer);
        }
        customer = new Customer();
        customer.setAccountID(dummyUpdateCustomer.getBranchCode());
        customer.setName(dummyUpdateCustomer.getName());
        customer.setBranch(branch);
        customer.setId(dummyUpdateCustomer.getId());
        customer.setBalance(dummyUpdateCustomer.getBalance());
        return customerDao.updateCustomer(customer);
    }

    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
