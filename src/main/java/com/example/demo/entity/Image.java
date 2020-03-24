package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.xml.soap.Text;

@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url",columnDefinition = "text")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false)
    private ProductEntity productEntity;

}
