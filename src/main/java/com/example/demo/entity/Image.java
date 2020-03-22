package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.xml.soap.Text;

@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url",length = 1000)
    private String url;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private ProductEntity productEntity;

}
