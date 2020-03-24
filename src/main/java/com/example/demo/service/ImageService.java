package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public List<Image> getAllImageByProductID(long productID){
        List<Image> list = imageRepository.findImageByProductId(productID);
        return list;
    }
}
