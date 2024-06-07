package com.example.demo61_account_thymeleaf.controller;

import com.example.demo61_account_thymeleaf.entity.Department;
import com.example.demo61_account_thymeleaf.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("product")
    public String getAll(Model model, Pageable pageable){
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        String page = "department-list-client";
        model.addAttribute("page",page);
        return "client-index";
    }
    @GetMapping("search")
    public String search(Model model,
                         @RequestParam String data){
        List<Department> departments = new ArrayList<>();
        if (data.equals("")){
            departments = departmentRepository.findAll();
        }
        model.addAttribute("departments",departments);
        model.addAttribute("page","product-list-client");
        return "client-index";
    }
}
