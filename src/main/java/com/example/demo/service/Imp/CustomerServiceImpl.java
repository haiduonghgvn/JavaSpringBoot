package com.example.demo.service.Imp;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerReponsitory;
import com.example.demo.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CustomerServiceImpl {
        @Autowired
    CustomerReponsitory customerRepo;
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
