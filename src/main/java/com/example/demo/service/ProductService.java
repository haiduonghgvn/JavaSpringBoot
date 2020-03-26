package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.entity.ProductEntity;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.ProductReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class ProductService {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ProductReponsitory productReponsitory;

    public List<ProductDto> getTopProduct() {
        List<ProductDto> productDtos = new ArrayList<>();
        for (ProductEntity p : productReponsitory.findFirst5ByID()) {
            ProductDto productDto = new ProductDto();
            productDto.setId(p.getId());
            productDto.setName(p.getName());
            productDto.setPrice(p.getPrice());
            productDto.setComment(p.getComment());
            try {
                productDto.setListImage(imageRepository.findImageByProductId(p.getId()));
                System.out.println(productDto.getListImage().toString());
            } catch (Exception e) {
                productDto.setListImage(new ArrayList<Image>());
            }
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public List<ProductEntity> findAll() {

        List<ProductEntity> productEntity = productReponsitory.findAll();

        return productEntity;
    }

    public ProductEntity findById(long id) {
        ServiceResult result = new ServiceResult();
        ProductEntity productEntity = productReponsitory.findById(id).orElse(null);

        return productEntity;
    }
    public ServiceResult save(ProductEntity productEntity){
        ServiceResult result = new ServiceResult();
        result.setData(productReponsitory.save(productEntity));
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
