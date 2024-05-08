package com.example.demo61_account_thymeleaf.repository;

import com.example.demo61_account_thymeleaf.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByName(String name);
}
