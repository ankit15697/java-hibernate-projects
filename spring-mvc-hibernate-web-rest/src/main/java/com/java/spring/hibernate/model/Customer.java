package com.java.spring.hibernate.model;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="balance")
    private double balance;
    @Column(name="accountID")
    private String accountID;

    @ManyToOne
    @JoinColumn(name="branchCode")
    private Branch branch;

    public Customer() {
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String  branchCode) {
        Random random = new Random();
        this.accountID = branchCode + String.format("%04d", random.nextInt(10000));;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Customer ID : " + id + " Name : " + name + " Branch Name : " + branch.getBranchName() + " Account Number : " + accountID + " Balance :"+ balance ;
    }
}