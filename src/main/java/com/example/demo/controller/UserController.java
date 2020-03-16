package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.model.request.DeleteRequest;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private CustomerService customerService;
    /* ---------------- GET ALL CUSTOMER ------------------------ */
    @GetMapping("/customers")
    public ResponseEntity<ServiceResult> findAllCustomer() {
        return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
    }
    /* ---------------- GET CUSTOMER BY ID ------------------------ */
    @GetMapping("/customers/{id}")
    public ResponseEntity<ServiceResult> findById(@PathVariable int id) {
        return new ResponseEntity<ServiceResult>(customerService.findById(id), HttpStatus.OK);
    }
    /* ---------------- CREATE NEW CUSTOMER ------------------------ */
    @PostMapping("/customers")
    public ResponseEntity<ServiceResult> create(@RequestBody Customer customer) {
        return new ResponseEntity<ServiceResult>(customerService.create(customer), HttpStatus.OK);
    }

    /* ---------------- UPDATE CUSTOMER ------------------------ */
    @PutMapping("/customers")
    public ResponseEntity<ServiceResult> update(@RequestBody Customer customer) {
        return new ResponseEntity<ServiceResult>(customerService.update(customer), HttpStatus.OK);
    }
    @DeleteMapping("/customers")
    public ResponseEntity<ServiceResult> delete(@RequestBody DeleteRequest request) {
        return new ResponseEntity<ServiceResult>(customerService.delete(request.getId()), HttpStatus.OK);
    }

}
