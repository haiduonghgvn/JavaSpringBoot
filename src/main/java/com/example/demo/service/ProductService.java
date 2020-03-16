package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.ProductEntity;
import com.example.demo.responsitory.ProductReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductReponsitory productReponsitory;

    public ServiceResult findAll(){
        ServiceResult result = new ServiceResult();
        result.setData(productReponsitory.findAll());
        return result;
    }
    public ServiceResult findById(long id){
        ServiceResult result = new ServiceResult();
        ProductEntity productEntity = productReponsitory.findById(id).orElse(null);
        result.setData(productEntity);
        return result;
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
