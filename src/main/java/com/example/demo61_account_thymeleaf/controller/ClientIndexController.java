package com.example.demo61_account_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// @Controller cho thymeleaf
@Controller
public class ClientIndexController {

    @GetMapping
    public String clientIndex(Model model) {
        String page = "client-index";
        model.addAttribute("page",page);
        return "client-index";
    }
    @GetMapping("/contact")
    public String clientContact(Model model){
        String page = "client-contact";
        model.addAttribute("page",page);
        return "client-index";
    }
}
