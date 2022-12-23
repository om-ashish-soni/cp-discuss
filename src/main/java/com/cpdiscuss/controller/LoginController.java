package com.cpdiscuss.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping("/")
    public List<Integer> findAll()  
    {  
        List<Integer> lst=new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            lst.add(i);
        }
        return lst;
    }  
}
