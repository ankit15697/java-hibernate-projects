package com.java.spring.hibernate.dao;

import com.java.spring.hibernate.model.Branch;
import com.java.spring.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BranchDao {
    public String addBranch(String branchName){
        Session session = getSession();
        session.beginTransaction();

        Branch branch = new Branch();
        branch.setBranchName(branchName);
        session.persist(branch);
        session.getTransaction().commit();
        session.close();
        return "Branch has been successfully added  " + branch;
    }

    public String removeBranch(String id){
        Session session = getSession();
        session.beginTransaction();
        Branch branch = (Branch) session.get(Branch.class,id);

        if(branch != null) {
            session.delete(branch);
            session.getTransaction().commit();
            session.close();
            return "Branch with id : " + id + " has been successfully deleted ";
        }
        session.close();
        return "Error !!!  404 No Branch found with this Id !!!";
    }
    public List<Branch> showBranches(){
        Session session = getSession();
        session.beginTransaction();
        List<Branch> branches= session.createQuery("from Branch ").list();
        session.getTransaction().commit();
        session.close();
        return branches;
    }
    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
