package com.example.demo61_account_thymeleaf.repository;

import com.example.demo61_account_thymeleaf.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer>  {
    Product findByName(String name);
}
