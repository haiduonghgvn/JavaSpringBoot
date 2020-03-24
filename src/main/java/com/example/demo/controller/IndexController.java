package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.ProductEntity;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;


    @GetMapping(value = {"/customer"})
    public String postCustomerAll(Model model) {
        List<Customer> c = (List<Customer>) customerService.findAll();
        model.addAttribute("customers", c);
        return "Customer";
    }

    @GetMapping(value = {"/index"})
    public String postProductAll(Model model) {

        List<ProductEntity> p = (List<ProductEntity>) productService.findAll();
        model.addAttribute("products", p);
        return "index";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam("id") Long userId, Model model) {
        productService.delete(userId);
        return "redirect:/index";
    }

    @RequestMapping(value = "/deleteU", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Long userId, Model model) {
        customerService.delete(userId);
        return "redirect:/customer";
    }


    @RequestMapping(value = "/profile",method =RequestMethod.GET)
    public String profile(@RequestParam ("id") Long userId, Model model){
        model.addAttribute("profile", customerService.findById(userId));
        return "editCustomer";
    }


}
