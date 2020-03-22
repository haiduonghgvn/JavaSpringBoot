package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.ProductEntity;
import com.example.demo.responsitory.CustomerReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerReponsitory customerRepo;
//    public List<Customer> getTopProduct(){
//        List<Customer> customers = new ArrayList<>();
//        customers = customerRepo.c();
//
//        return customers;
//    }

    public List<Customer> findAll(){

        List<Customer> customers = customerRepo.findAll();
        return customers;
    }
    public Customer findById(long id){
        ServiceResult result = new ServiceResult();
        Customer customer = customerRepo.findById(id).orElse(null);

        return customer;
    }
    public ServiceResult create(Customer customer) {
        ServiceResult result = new ServiceResult();
        result.setData(customerRepo.save(customer));
        return result;
    }
    public ServiceResult update(Customer customer) {
        ServiceResult result = new ServiceResult();
        if (!customerRepo.findById(customer.getId()).isPresent()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Customer Not Found");
        } else {
            result.setData(customerRepo.save(customer));
        }
        return result;
    }
    public ServiceResult delete(long id) {
        ServiceResult result = new ServiceResult();
        Customer customer = customerRepo.findById(id).orElse(null);
        if (customer == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Customer Not Found");
        } else {
            customerRepo.delete(customer);
            result.setMessage("success");
        }
        return result;
    }

}
