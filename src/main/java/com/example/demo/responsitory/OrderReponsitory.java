package com.example.demo.responsitory;

import com.example.demo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderReponsitory extends JpaRepository<OrderEntity,Long> {
}
