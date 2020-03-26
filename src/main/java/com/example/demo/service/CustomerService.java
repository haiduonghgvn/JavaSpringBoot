package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.model.dto.CustomerDto;
import com.example.demo.repository.CustomerReponsitory;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerReponsitory customerRepo;

    @Autowired
    RoleRepository roleRepository;


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


    public CustomerDto findUserByName(String userName){
            Customer customer =customerRepo.findUserByName(userName);
            CustomerDto customerDto = new CustomerDto();
            customerDto.setAvatar(customer.getAvatar());
            customerDto.setEmail(customer.getEmail());
            customerDto.setPhone(customer.getPhone());
            customerDto.setName(customer.getName());
            customerDto.setId(customer.getId());
            customerDto.setPassword(customer.getPassword());
            customerDto.setRoleList(roleRepository.findRoleByUserId(customerDto.getId()));
        return customerDto;
    }


}
