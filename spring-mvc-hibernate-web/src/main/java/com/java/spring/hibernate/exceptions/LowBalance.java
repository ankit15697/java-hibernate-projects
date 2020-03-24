package com.java.spring.hibernate.exceptions;

public class LowBalance extends RuntimeException {
    public LowBalance(String msg) {
        super(msg);
    }
}
