package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByName(String name);
//
//    @Query(value = "SELECT * FROM customer WHERE name=?1",nativeQuery = true)
//    Customer findUserByName(String userName);
}
