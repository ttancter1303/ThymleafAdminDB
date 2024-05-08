package com.example.demo61_account_thymeleaf.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProductController {
    @GetMapping("product")
    public String getAll(Model model){
        String page = "product-list-client";
        model.addAttribute("page",page);
        return "client-index";
    }
}
