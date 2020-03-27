package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Role;
import com.example.demo.model.dto.CustomerDto;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    RoleRepository roleRepository;


//    public List<Customer> getTopProduct(){
//        List<Customer> customers = new ArrayList<>();
//        customers = customerRepo.c();
//
//        return customers;
//    }

    public List<Customer> findAll() {

        List<Customer> customers = customerRepo.findAll();
        return customers;
    }

    public Customer findById(long id) {
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


    public CustomerDto findUserByName(String userName) {
        Customer customer = customerRepo.findByName(userName);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setId(customer.getId());
        customerDto.setPassword(customer.getPassword());
        customerDto.setRoleList(roleRepository.findRoleByUserId(customerDto.getId()));
        return customerDto;
    }


    public ServiceResult registerNormalUser(Customer user) {

        List<Role> roleList = new ArrayList<>();
        roleList.add(roleRepository.findByName("user") );
        Set set = new HashSet(roleList);
        user.setRoles(set);
        return create(user);

    }
}
