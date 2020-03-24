package com.java.spring.hibernate.main;

import com.java.spring.hibernate.controller.Controller;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainActivity {
    public static void main(String args[]) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Controller controller = context.getBean(Controller.class);
        controller.performOperations();
        context.close();
    }
}
