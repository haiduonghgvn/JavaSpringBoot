package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReponsitory extends JpaRepository<ProductEntity,Long> {
    @Query(value = "SELECT * FROM storeau.products limit 0,5;",nativeQuery = true)
    public List<ProductEntity> findFirst5ByID();

    List<ProductEntity> findByName(String name);


}
