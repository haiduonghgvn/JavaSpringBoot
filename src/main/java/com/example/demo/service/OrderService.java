package com.example.demo.service;

import com.example.demo.entity.OrderEntity;
import com.example.demo.repository.OrderReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderReponsitory orderReponsitory;

    public ServiceResult findAll(){
        ServiceResult result = new ServiceResult();
        result.setData(orderReponsitory.findAll());
        return result;
    }
    public ServiceResult findById(long id){
        ServiceResult result = new ServiceResult();
        OrderEntity orderEntity = orderReponsitory.findById(id).orElse(null);
        result.setData(orderEntity);
        return result;
    }
    public ServiceResult create(OrderEntity orderEntity) {
        ServiceResult result = new ServiceResult();
        result.setData(orderReponsitory.save(orderEntity));
        return result;
    }
    public ServiceResult update(OrderEntity orderEntity) {
        ServiceResult result = new ServiceResult();
        if (!orderReponsitory.findById(orderEntity.getId()).isPresent()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Customer Not Found");
        } else {
            result.setData(orderReponsitory.save(orderEntity));
        }
        return result;
    }
    public ServiceResult delete(long id) {
        ServiceResult result = new ServiceResult();
        OrderEntity orderEntity = orderReponsitory.findById(id).orElse(null);
        if (orderEntity == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Customer Not Found");
        } else {
            orderReponsitory.delete(orderEntity);
            result.setMessage("success");
        }
        return result;
    }
}
