package com.example.demo61_account_thymeleaf.controller;

import com.example.demo61_account_thymeleaf.entity.Department;
import com.example.demo61_account_thymeleaf.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    public String getAllDepartments(Model model, Pageable pageable) {
        Page<Department> departmentPage = departmentRepository.findAll(pageable);
        List<Department> departments = departmentPage.toList();
        model.addAttribute("departments", departments);
        model.addAttribute("totalPage", departmentPage.getTotalPages());
        model.addAttribute("currentPage", pageable.getPageNumber());
        return "department-index";
    }


    @GetMapping("/departments/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            model.addAttribute("department", department.get());
            return "department-form";
        } else {
            return "redirect:/departments";
        }
    }

    @GetMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            departmentRepository.deleteById(id);
        } else {
            System.out.println("Not found department with id = " + id);
        }
        return "redirect:/departments";
    }

    @GetMapping("/departments/new")
    public String showCreateForm(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "department-form";
    }

    @PostMapping("/departments")
    public String saveDepartment(@ModelAttribute("department") Department department) {
        departmentRepository.save(department);
        return "redirect:/departments";
    }
}
