package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "shop/index")
    public ModelAndView listCart(){
        ModelAndView modelAndView = new ModelAndView("shop/index");
        List<ProductEntity> list = productService.findAll();
        modelAndView.addObject("listResult",list);
        return modelAndView;

    }

    @RequestMapping("/shop/cart/add")
    public ModelAndView addCart(@RequestParam("id") Long id, HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("shop/cart");
        ProductEntity productEntity =productService.findById(id);
        Cart cart = new Cart();
        List<Cart> list = (List<Cart>) httpSession.getAttribute("cart");
        if(list == null){
            list =  new ArrayList<Cart>();
        }
        if (productEntity != null){
            cart.ToCart(productEntity);
            BigDecimal total = addCart(list,cart);
            modelAndView.addObject("total",total);
            httpSession.setAttribute("cart",list);
        }

        modelAndView.addObject("listCart",list);
        return modelAndView;
    }

    private BigDecimal addCart(List<Cart> list, Cart cart) {
        BigDecimal total = new BigDecimal(0);
        boolean isExit = false;
        for (Cart c : list){
            if (c.equals(cart)){
                c.setQuamlity(c.getQuamlity()+1);
                isExit =true;
            }
             total= total.add(c.getPrice().multiply(new BigDecimal(c.getQuamlity())));
        }
        if (isExit == false){
            list.add(cart);
            total= total.add(cart.getPrice().multiply(new BigDecimal(cart.getQuamlity())));

        }
            return total;
    }

}
