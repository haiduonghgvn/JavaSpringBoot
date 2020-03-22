package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.ProductEntity;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.responsitory.ProductReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class ProductService {
    @Autowired
    ProductReponsitory productReponsitory;
    public List<ProductEntity> getTopProduct(){
        List<ProductEntity> productDtos = new ArrayList<>();
        productDtos = productReponsitory.findFirst5ByID();

        return productDtos;
    }

    public List<ProductEntity> findAll(){

        List<ProductEntity> productEntity = productReponsitory.findAll();
        return productEntity;
    }
    public ProductEntity findById(long id){
        ServiceResult result = new ServiceResult();
        ProductEntity productEntity = productReponsitory.findById(id).orElse(null);

        return productEntity;
    }
    public ServiceResult create(ProductEntity productEntity) {
        ServiceResult result = new ServiceResult();
        result.setData(productReponsitory.save(productEntity));
        return result;
    }
    public ServiceResult update(ProductEntity productEntity) {
        ServiceResult result = new ServiceResult();
        if (!productReponsitory.findById(productEntity.getId()).isPresent()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Customer Not Found");
        } else {
            result.setData(productReponsitory.save(productEntity));
        }
        return result;
    }
    public ServiceResult delete(long id) {
        ServiceResult result = new ServiceResult();
        ProductEntity productEntity = productReponsitory.findById(id).orElse(null);
        if (productEntity == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Customer Not Found");
        } else {
            productReponsitory.delete(productEntity);
            result.setMessage("success");
        }
        return result;
    }
}
