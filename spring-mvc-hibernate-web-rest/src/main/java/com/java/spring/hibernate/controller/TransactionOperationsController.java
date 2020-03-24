package com.java.spring.hibernate.controller;


import com.java.spring.hibernate.dao.TransactionDao;
import com.java.spring.hibernate.dummy.DummyData;
import com.java.spring.hibernate.exceptions.InvalidCustomer;
import com.java.spring.hibernate.exceptions.LowBalance;
import com.java.spring.hibernate.model.Customer;
import com.java.spring.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TransactionOperationsController {
    private TransactionDao transactionDao;

    public TransactionOperationsController() {
        transactionDao = new TransactionDao();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    }

    @RequestMapping(value = UrlController.DEBIT_BALANCE, method = RequestMethod.POST)
    public @ResponseBody Customer debitBalance(@RequestBody DummyData dummyData) {
        Session session = getSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class, dummyData.getId());
        session.close();
        if (customer == null) {
            throw new InvalidCustomer("No customer with this id is present : " + customer);
        }
        if (customer.getBalance() < dummyData.getBalance()) {
            throw new LowBalance("!!! Not Enough balance in account ");
        }
        return transactionDao.debitBalance(dummyData);
    }

        @RequestMapping(value = UrlController.CREDIT_BALANCE, method = RequestMethod.POST)
        public @ResponseBody Customer creditBalance(@RequestBody DummyData dummyData) {
            Session session = getSession();
            session.beginTransaction();
            Customer customer = (Customer) session.get(Customer.class,dummyData.getId());
            session.close();
            if(customer == null) {
                throw new InvalidCustomer("No customer with this id is present : " + customer);
            }
            return transactionDao.creditBalance(dummyData);
        }
    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
