package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    //    Get Information
    @RequestMapping(value = "/detail")
    public String detailProduct(Model model, @RequestParam("id") Long id, HttpSession httpSession) {
        model.addAttribute("productDetail", productService.findById(id));
        return "ProductDetais";
    }

    @GetMapping(value = "/store")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("fiveProduct", productService.getTopProduct());
        modelAndView.setViewName("indexStore");
        return modelAndView;
    }



    //    =================== Add Cart =================
    @RequestMapping("/cart/add")
    public ModelAndView addCart(@RequestParam("id") Long id, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("shop/CartShopping");
        ProductEntity productEntity = productService.findById(id);
        Cart cart = new Cart();
        List<Cart> list = (List<Cart>) httpSession.getAttribute("cart");
        if (list == null) {
            list = new ArrayList<Cart>();
        }
        if (productEntity != null) {
            cart.ToCart(productEntity);
            BigDecimal total = addCart(list, cart);
            modelAndView.addObject("total", total);
            httpSession.setAttribute("cart", list);
        }
        modelAndView.addObject("listCart", list);
        if (list.size() == 0) {
            modelAndView.addObject("count", '0');
        } else {
            modelAndView.addObject("count", list.size());
        }
        return modelAndView;
    }

    private BigDecimal addCart(List<Cart> list, Cart cart) {
        BigDecimal total = new BigDecimal(0);
        int count = 0;
        boolean isExit = false;
        for (Cart c : list) {
            if (c.equals(cart)) {
                c.setQuamlity(c.getQuamlity() + 1);
                count = count + 1;
                isExit = true;
            }
            total = total.add(c.getPrice().multiply(new BigDecimal(c.getQuamlity())));
        }
        if (isExit == false) {
            list.add(cart);
            total = total.add(cart.getPrice().multiply(new BigDecimal(cart.getQuamlity())));
        }

        return total;
    }


//   < ================= REMOVE CART ====================>

    @RequestMapping("cart/remove")
    public ModelAndView removeCart(@RequestParam Long id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("shop/CartShopping");
        List<Cart> list = (List<Cart>) session.getAttribute("cart");
        if (list != null) {
            BigDecimal total = removeCartItem(list, id);
            modelAndView.addObject("total", total);
            session.setAttribute("cart", list);

        }
        modelAndView.addObject("listCart", list);
        return modelAndView;
    }

    private BigDecimal removeCartItem(List<Cart> list, Long id) {
        BigDecimal total = new BigDecimal(0);
        Cart temp = null;
        for (Cart c : list) {
            if (c.getId() == id) {
                temp = c;
                continue;
            }
            total = total.add(c.getPrice().multiply(new BigDecimal(c.getQuamlity())));
        }
        if (temp != null) {
            list.remove(temp);
        }
        return total;
    }

}
