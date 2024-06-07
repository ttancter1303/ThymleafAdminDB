package com.example.demo61_account_thymeleaf.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String address;
    private String role;
    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;


}
