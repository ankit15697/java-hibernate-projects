package com.java.spring.hibernate.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.util.Random;

// This class will be responsible for generating the branch code randomly
public class IdGenerator implements IdentifierGenerator {
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        Random random = new Random();
        String id = String.format("%04d", random.nextInt(10000));
        return  id;
    }
}
