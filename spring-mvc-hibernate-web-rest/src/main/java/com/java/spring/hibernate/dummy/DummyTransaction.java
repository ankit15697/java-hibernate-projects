package com.java.spring.hibernate.dummy;

public class DummyTransaction {
    private int id;
    private Double balance;

    public DummyTransaction(int id, Double balance) {
        this.id = id;
        this.balance = balance;
    }

    public DummyTransaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
