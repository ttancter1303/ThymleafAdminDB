package com.example.demo61_account_thymeleaf.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

@Table(name = "category")
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
