package com.example.demo61_account_thymeleaf.repository;

import com.example.demo61_account_thymeleaf.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
