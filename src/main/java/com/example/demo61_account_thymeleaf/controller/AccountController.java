package com.example.demo61_account_thymeleaf.controller;

import com.example.demo61_account_thymeleaf.dto.AccountDTO;
import com.example.demo61_account_thymeleaf.entity.Account;
import com.example.demo61_account_thymeleaf.repository.AccountRepository;
import com.example.demo61_account_thymeleaf.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @GetMapping
    public String clientIndex(Model model) {
        String page = "account-index";
        model.addAttribute("page",page);
        return "account-index";
    }
    @GetMapping("/account")
    public String getAllAccount(Model model, Pageable pageable) {
        String page = "admin-product";
        Page<Account> accountPage = accountRepository.findAll(pageable);
        List<Account> accounts = accountPage.toList();

        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account obj : accounts) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(obj.getId());
            accountDTO.setFullName(obj.getFullName());
            accountDTO.setAddress(obj.getAddress());

            if (obj.getDepartment() !=null ){
                accountDTO.setDepartment(obj.getDepartment().getDepartmentName());
            }
            accountDTOS.add(accountDTO);
            System.out.println(accountDTOS);
        }

        model.addAttribute("accounts", accountDTOS);
        model.addAttribute("totalPage",accountPage.getTotalPages());
        model.addAttribute("currentPage",pageable.getPageNumber());
        model.addAttribute("page",page);
        System.out.println(pageable.getPageNumber());

        return "admin-index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Account> opAccount = accountRepository.findById(id);
        if (opAccount.isEmpty()) {
            System.out.println("Not found Account with id = " + id);
        }
        accountRepository.deleteById(id);
        return "redirect:/account";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            model.addAttribute("account", account.get());
            model.addAttribute("departments", departmentRepository.findAll());
            return "account/form";
        } else {
            return "redirect:/accounts";
        }
    }
    @GetMapping("delete/product/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            System.out.println("Not found account with id = " + id);
        }
        accountRepository.deleteById(id);
        return "redirect:/admin/product";
    }

    @PostMapping
    public String saveAccount(@ModelAttribute("account") Account account) {
        accountRepository.save(account);
        return "redirect:/accounts";
    }


}
