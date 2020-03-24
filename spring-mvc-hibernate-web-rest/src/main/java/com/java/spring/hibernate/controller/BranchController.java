package com.java.spring.hibernate.controller;

import com.java.spring.hibernate.dao.BranchDao;
import com.java.spring.hibernate.model.Branch;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("/branch")
public class BranchController {
    private BranchDao branchDao;

    public BranchController() {
        branchDao = new BranchDao();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Branch> getAllBranches() {
        return branchDao.showBranches();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody Branch addBranch(@RequestBody Branch branch) {
       return branchDao.addBranch(branch);
    }
}
