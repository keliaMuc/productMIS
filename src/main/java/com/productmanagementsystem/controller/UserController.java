package com.productmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @RequestMapping(value = {"/userdashboard"}, method = RequestMethod.GET)
    public String homePage(){
        return "admin/home";
    }


}
