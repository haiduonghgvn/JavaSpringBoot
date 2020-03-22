package com.example.demo.responsitory;

import com.example.demo.entity.Customer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerReponsitory extends JpaRepository<Customer,Long> {
}
