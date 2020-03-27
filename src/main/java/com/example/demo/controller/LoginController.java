package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    CustomerService customerService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    // register without check duplicate
    @PostMapping("/register")
    public String register(@RequestParam("name") String userName,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("phone") String phone) {

        Customer customerTemp = new Customer();
        customerTemp.setName(userName);
        customerTemp.setPassword(passwordEncoder.encode(password));
        customerTemp.setEmail(email);
        customerTemp.setPhone(phone);
        customerService.registerNormalUser(customerTemp);

        return "login";
    }
}
