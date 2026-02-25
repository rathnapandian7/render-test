package com.uv.core.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Double price;

    private String category;

    private Integer stock;

    private String status;

    @Column(length = 2000)
    private String description;

    private String imageUrl;


}
