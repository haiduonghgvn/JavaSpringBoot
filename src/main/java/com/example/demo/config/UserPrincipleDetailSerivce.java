package com.example.demo.config;

import com.example.demo.entity.Customer;
import com.example.demo.model.dto.CustomerDto;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserPrincipleDetailSerivce  implements UserDetailsService {

    @Autowired
    CustomerService  customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomerDto user = customerService.findUserByName(username);
        UserPrincipleDetail userPrincipleDetail= new UserPrincipleDetail(user);
        return userPrincipleDetail;
    }
}
