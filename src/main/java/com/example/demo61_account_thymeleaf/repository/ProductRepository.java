package com.example.demo61_account_thymeleaf.repository;

import com.example.demo61_account_thymeleaf.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer>  {
    Product findByName(String name);
    List<Product> getAllByName(String productName);

}
