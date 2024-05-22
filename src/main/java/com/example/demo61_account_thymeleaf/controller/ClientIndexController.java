package com.example.demo61_account_thymeleaf.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// @Controller cho thymeleaf
@Controller
public class ClientIndexController {

    @GetMapping
    public String clientIndex(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
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
