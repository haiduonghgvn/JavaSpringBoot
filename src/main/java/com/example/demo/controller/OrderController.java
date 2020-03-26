package com.example.demo.controller;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.DeleteRequest;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    /* ---------------- GET ALL Order ------------------------ */
    @GetMapping("/orders")
    public ResponseEntity<ServiceResult> findAllCustomer() {
        return new ResponseEntity<ServiceResult>(orderService.findAll(), HttpStatus.OK);
    }
    /* ---------------- GET Order BY ID ------------------------ */
    @GetMapping("/orders/{id}")
    public ResponseEntity<ServiceResult> findById(@PathVariable int id) {
        return new ResponseEntity<ServiceResult>(orderService.findById(id), HttpStatus.OK);
    }
    /* ---------------- CREATE NEW Order ------------------------ */
    @PostMapping("/orders")
    public ResponseEntity<ServiceResult> create(@RequestBody OrderEntity orderEntity) {
        return new ResponseEntity<ServiceResult>(orderService.create(orderEntity), HttpStatus.OK);
    }

    /* ---------------- UPDATE Order ------------------------ */
    @PutMapping("/orders")
    public ResponseEntity<ServiceResult> update(@RequestBody OrderEntity orderEntity) {
        return new ResponseEntity<ServiceResult>(orderService.update(orderEntity), HttpStatus.OK);
    }

//    ----------------------------- DELETE Order -----------------------//
    @DeleteMapping("/orders")
    public ResponseEntity<ServiceResult> delete(@RequestBody DeleteRequest request) {
        return new ResponseEntity<ServiceResult>(orderService.delete(request.getId()), HttpStatus.OK);
    }
}
