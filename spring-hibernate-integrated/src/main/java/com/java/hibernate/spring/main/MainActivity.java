package com.java.hibernate.spring.main;

import com.java.hibernate.spring.dao.PersonDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainActivity {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        PersonDao personDao = context.getBean(PersonDao.class);
        personDao.performOperations();
        context.close();
    }
}
