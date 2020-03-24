package com.java.spring.hibernate.dao;

import com.java.spring.hibernate.model.Branch;
import com.java.spring.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BranchDao {
    public Branch addBranch(Branch branch){
        Session session = getSession();
        session.beginTransaction();
        session.persist(branch);
        session.getTransaction().commit();
        session.close();
        return branch;
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
