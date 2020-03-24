package com.java.hibernate.controller;

import com.java.hibernate.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class HibernateController {
    private SessionFactory sessionFactory;
    private void setup() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    private void exit() {
        sessionFactory.close();
    }

    private void create() {
        Person person = new Person();
        person.setName("KP");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(person);

        session.getTransaction().commit();
        session.close();
    }

    private void read() {
        Session session = sessionFactory.openSession();
        System.out.println(session.createQuery("FROM Person").list());
        session.close();
    }

    private void update() {
        Person person = new Person();
        person.setName("Ram");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Person oldPerson = (Person) session.load(Person.class, 1);
        oldPerson.setName(person.getName());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + oldPerson);
    }

    private void delete() {
        Person person = new Person();
        person.setId(2);
        @SuppressWarnings("unchecked")
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(person);

        session.getTransaction().commit();
        session.close();
    }
    public void performOperations() {
        Scanner sc = new Scanner(System.in);
        setup();
        while(true) {
            int option;
            System.out.println("Enter your choice : ");
            System.out.println("1. Create \n 2. read\n 3. Update\n 4. Delete");
            option = sc.nextInt();
            switch (option) {
                case 1:
                        create();
                        break;
                case 2:
                     read();
                     break;
                case 3:
                        update();
                        break;
                case 4:
                        delete();
                        break;
                default:
                        exit();
                        return;
            }
        }
    }
}
