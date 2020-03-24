package com.java.hibernate.spring.dao;

import com.java.hibernate.spring.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Scanner;

public class PersonDao {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private void exit() {
        sessionFactory.close();
    }

    private void create() {
        Person person = new Person();
        person.setName("BP");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(person);
        tx.commit();
        session.close();
    }

    private List<Person> read() {
        Session session = this.sessionFactory.openSession();
        List<Person> personList = session.createQuery("from Person").list();
        session.close();
        return personList;
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
        create();
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
                    System.out.println(read());
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
