package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ImageService;
import com.example.demo.service.ProductService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    final
    ImageService imageService;
    final
    CategoryService categoryService;
    private final
    ProductService productService;
    private final
    CustomerService customerService;

    public IndexController(ProductService productService, CustomerService customerService, ImageService imageService, CategoryService categoryService) {
        this.productService = productService;
        this.customerService = customerService;
        this.imageService = imageService;
        this.categoryService = categoryService;
    }

//    ======================Image+=================
    @RequestMapping("/image")
    public String index(Model model,@RequestParam(value = "id",required = true) Long id) {
    //        model.addAttribute("products",productService.findAll());
        model.addAttribute("product", productService.findAll());
        model.addAttribute("myFile", new MyFile());
        model.addAttribute("id_product",id);
        return "addImage";
    }
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@PathParam(value = "id_product" ) Long id, MyFile myFile, Model model) {
        Image image =new Image();
        model.addAttribute("message", "Upload success");
        model.addAttribute("description", myFile.getDescription());
        ProductEntity productEntity = new ProductEntity();
        try {
            MultipartFile multipartFile = myFile.getMultipartFile();
            String fileName = multipartFile.getOriginalFilename();
            File file = new File(this.getFolderUpload(), fileName);
            multipartFile.transferTo(file);

                    image.setUrl("/Uploads/"+fileName);
                    image.setProductEntity(productService.findById(id));
                    imageService.save(image);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Upload failed");
        }
        return "result";
    }
    public File getFolderUpload() {
        File folderUpload = new File( "C:Users/Ominous/Documents/JavaSpringBoot/src/main/resources/static/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    //    =========================================
    @GetMapping(value = {"/customer"})
    public String postCustomerAll(Model model) {
        List<Customer> c = (List<Customer>) customerService.findAll();
        model.addAttribute("customers", c);
        return "Customer";
    }

    @GetMapping(value = {"/","/index"})
    public String postProductAll(Model model) {
        List<ProductEntity> p = (List<ProductEntity>) productService.findAll();
        model.addAttribute("products", productService.findAll());
        return "adminIndex";
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

    @GetMapping("/profile")
    public String profile(@RequestParam ("id") Long userId, Model model){
        model.addAttribute("profile", customerService.findById(userId));
        return "editCustomer";
    }

    @RequestMapping("/news")
    public String showNewProductPage(Model model) {
        List<Category> listCategory  = categoryService.listCategory();
        model.addAttribute("category",listCategory);
        model.addAttribute("product", new ProductEntity());
        return "addProduct";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") ProductEntity product,Model model) {
        model.addAttribute("productID",product.getId());
        productService.save(product);
        return "redirect:/image"+product.getId();
    }


}
