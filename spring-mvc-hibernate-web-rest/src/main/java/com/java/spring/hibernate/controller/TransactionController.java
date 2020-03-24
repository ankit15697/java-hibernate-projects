package com.java.spring.hibernate.controller;


import com.java.spring.hibernate.dao.TransactionDao;
import com.java.spring.hibernate.dummy.DummyTransaction;
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
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionDao transactionDao;

    public TransactionController() {
        transactionDao = new TransactionDao();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    }

    @RequestMapping(value = "/debit", method = RequestMethod.POST)
    public @ResponseBody Customer debitBalance(@RequestBody DummyTransaction dummyTransaction) {
        Session session = getSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class, dummyTransaction.getId());
        session.close();
        if (customer == null) {
            throw new InvalidCustomer("No customer with this id is present : " + customer);
        }
        if (customer.getBalance() < dummyTransaction.getBalance()) {
            throw new LowBalance("!!! Not Enough balance in account ");
        }
        return transactionDao.debitBalance(dummyTransaction);
    }

        @RequestMapping(value = "/credit", method = RequestMethod.POST)
        public @ResponseBody Customer creditBalance(@RequestBody DummyTransaction dummyTransaction) {
            Session session = getSession();
            session.beginTransaction();
            Customer customer = (Customer) session.get(Customer.class, dummyTransaction.getId());
            session.close();
            if(customer == null) {
                throw new InvalidCustomer("No customer with this id is present : " + customer);
            }
            return transactionDao.creditBalance(dummyTransaction);
        }
    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
