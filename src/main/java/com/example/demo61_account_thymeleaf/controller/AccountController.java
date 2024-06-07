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

    @GetMapping("/accounts")
    public String getAllAccounts(Model model, Pageable pageable) {
        Page<Account> accountPage = accountRepository.findAll(pageable);
        List<Account> accounts = accountPage.toList();

        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account obj : accounts) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(obj.getId());
            accountDTO.setFullName(obj.getFullName());
            accountDTO.setAddress(obj.getAddress());
            if (obj.getDepartment() != null) {
                accountDTO.setDepartment(obj.getDepartment().getDepartmentName());
            }
            accountDTOS.add(accountDTO);
        }

        int totalPage = accountPage.getTotalPages(); // Ensure totalPage is initialized

        model.addAttribute("accounts", accountDTOS);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", pageable.getPageNumber());

        return "account-index";
    }


    @GetMapping("/accounts/new")
    public String showCreateForm(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("username", ""); // Thêm trường username vào model và gán giá trị mặc định là rỗng
        model.addAttribute("password", ""); // Thêm trường password vào model và gán giá trị mặc định là rỗng
        return "account-form";
    }


    @GetMapping("/accounts/edit/{id}")
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

    @GetMapping("/accounts/delete/{id}")
    public String deleteAccount(@PathVariable Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            accountRepository.deleteById(id);
        } else {
            System.out.println("Not found account with id = " + id);
        }
        return "redirect:/accounts";
    }

    @PostMapping("/accounts")
    public String saveAccount(@ModelAttribute("account") Account account) {
        accountRepository.save(account);
        return "redirect:/accounts";
    }

}
