package com.example.demo61_account_thymeleaf.dto;

import com.example.demo61_account_thymeleaf.entity.Department;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AccountDTO {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String address;
    private Department department;

    public void setDepartment(String departmentName) {
    }
}
