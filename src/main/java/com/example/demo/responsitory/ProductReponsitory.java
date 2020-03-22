package com.example.demo.responsitory;

import com.example.demo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ProductReponsitory extends JpaRepository<ProductEntity,Long> {
    @Query(value = "SELECT * FROM storeau.products limit 0,5;",nativeQuery = true)
    public List<ProductEntity> findFirst5ByID();


}
