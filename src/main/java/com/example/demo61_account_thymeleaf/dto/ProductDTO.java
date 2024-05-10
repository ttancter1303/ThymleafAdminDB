package com.example.demo61_account_thymeleaf.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private long price;
    private String imagePath;
    private String categoryName;
}
