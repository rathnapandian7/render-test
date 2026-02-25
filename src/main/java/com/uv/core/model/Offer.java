package com.uv.core.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String discount;

    private String promoCode;

    @Column(length = 2000)
    private String description;

    private String imageUrl;
}
