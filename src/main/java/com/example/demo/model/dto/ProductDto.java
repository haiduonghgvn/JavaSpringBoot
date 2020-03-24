package com.example.demo.model.dto;
import java.math.BigDecimal;
import java.util.List;

import com.example.demo.entity.Image;
import com.example.demo.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String comment;
    private List<Image> listImage;

}
