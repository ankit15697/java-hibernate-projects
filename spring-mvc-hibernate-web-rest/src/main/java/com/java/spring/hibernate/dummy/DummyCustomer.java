package com.java.spring.hibernate.dummy;

public class DummyCustomer {
    private String name;
    private Double balance;
    private String branchCode;

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

    public DummyCustomer(String name, Double balance, String branchCode) {
        this.name = name;
        this.balance = balance;
        this.branchCode = branchCode;
    }

    public DummyCustomer() {
    }
}
