package com.example.demo.controller;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class ProdDetailController {
    @Autowired
    ProductService productService;
    @RequestMapping(value = "/detail")
    public String detailProduct(Model model, @RequestParam("id") Long id)
    {
        model.addAttribute("productDetail", productService.findById(id));
        return "ProductDetais";
    }
}
