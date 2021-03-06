package com.java.spring.hibernate.dummy;

public class DummyUpdateCustomer {
    private int id;
    private String name;
    private Double balance;
    private String branchCode;

    public DummyUpdateCustomer() {
    }

    public DummyUpdateCustomer(int id, String name, Double balance, String branchCode) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.branchCode = branchCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
}
