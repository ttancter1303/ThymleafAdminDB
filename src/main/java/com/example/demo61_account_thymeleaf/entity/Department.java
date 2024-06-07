package com.example.demo61_account_thymeleaf.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String departmentName;
    private int numPeople;
    @OneToMany(mappedBy = "department")
    private List<Account> accounts = new ArrayList<>();
}
