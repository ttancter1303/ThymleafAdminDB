package com.example.demo61_account_thymeleaf.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long price;
    private String imagePath;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    Category category;
}
