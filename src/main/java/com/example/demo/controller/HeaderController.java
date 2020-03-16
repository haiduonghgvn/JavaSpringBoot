package com.example.demo.controller;

import com.example.demo.entity.ProductEntity;
import com.example.demo.responsitory.ProductReponsitory;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HeaderController {
    @Autowired
ProductService productService;
    @GetMapping("/header")
    public String listProduct(Model model){

            model.addAttribute("products",productService.findAll() );
            return "fragment/header";
    }
}
