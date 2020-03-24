package com.java.spring.hibernate.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GenericGenerator(name = "sequence_branch_id", strategy = "com.java.spring.hibernate.model.IdGenerator")
    @GeneratedValue(generator = "sequence_branch_id")
    @Column(name="branchCode")
    private String branchCode;

    @Column(name="branchName")
    private String branchName;

    public Branch() {
    }
    public Branch(String branchName) {
        this.branchName = branchName;
    }
    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public String toString() {
        return "Branch Code : " + branchCode + " : Branch Name : " + branchName;
    }
}
