package com.example.demo61_account_thymeleaf.service;

import com.example.demo61_account_thymeleaf.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {

    // abstract method, public access modifier
    List<Account> getAll();

}
