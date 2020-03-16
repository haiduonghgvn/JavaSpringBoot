package com.example.demo.controller;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.DeleteRequest;
import com.example.demo.service.ProductService;
import com.example.demo.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    /* ---------------- GET ALL PRODUCT ------------------------ */
    @GetMapping("/products")
    public ResponseEntity<ServiceResult> findAllCustomer() {
        return new ResponseEntity<ServiceResult>(productService.findAll(), HttpStatus.OK);
    }
    /* ---------------- GET PRODUCT BY ID ------------------------ */
    @GetMapping("/products/{id}")
    public ResponseEntity<ServiceResult> findById(@PathVariable int id) {
        return new ResponseEntity<ServiceResult>(productService.findById(id), HttpStatus.OK);
    }
    /* ---------------- CREATE NEW PRODUCT ------------------------ */
    @PostMapping("/products")
    public ResponseEntity<ServiceResult> create(@RequestBody ProductEntity productEntity) {
        return new ResponseEntity<ServiceResult>(productService.create(productEntity), HttpStatus.OK);
    }

    /* ---------------- UPDATE PRODUCT ------------------------ */
    @PutMapping("/products")
    public ResponseEntity<ServiceResult> update(@RequestBody ProductEntity productEntity) {
        return new ResponseEntity<ServiceResult>(productService.update(productEntity), HttpStatus.OK);
    }
    @DeleteMapping("/products")
    public ResponseEntity<ServiceResult> delete(@RequestBody DeleteRequest request) {
        return new ResponseEntity<ServiceResult>(productService.delete(request.getId()), HttpStatus.OK);
    }
}
