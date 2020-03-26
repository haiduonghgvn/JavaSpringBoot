package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReponsitory extends JpaRepository<Customer,Long> {
    Customer findByEmail(String email);
    @Query(value = "select u from Customer u where u.name=?1")
    Customer findUserByName(String userName);
}
