package com.java.spring.hibernate.exceptions;

public class InvalidCustomer  extends RuntimeException{
    public InvalidCustomer(String msg) {
        super(msg);
    }
}
