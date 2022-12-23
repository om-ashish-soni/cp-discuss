package com.cpdiscuss.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpdiscuss.model.User;
import com.cpdiscuss.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoOperations mongoOperations;

    @PostMapping("/login")
    //get request demo
    @RequestMapping("/")
    public List<Integer> findAll()  
    {  
        List<Integer> lst=new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            lst.add(i);
        }
        return lst;
    }  

    //post request demo
    @PostMapping("/abc")
    public List<String> doABC(HttpServletRequest req){
        List<String> lst=new ArrayList<>();
        lst.add("abc");
        User user = new User("om","123");
        mongoOperations.save(user);
        System.out.println(req.getParameter("first"));
        return lst;
    }
}
