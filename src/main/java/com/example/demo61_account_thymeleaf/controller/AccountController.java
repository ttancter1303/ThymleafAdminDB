package com.example.demo61_account_thymeleaf.controller;

import com.example.demo61_account_thymeleaf.entity.Account;
import com.example.demo61_account_thymeleaf.entity.Category;
import com.example.demo61_account_thymeleaf.entity.Product;
import com.example.demo61_account_thymeleaf.repository.AccountRepository;
import com.example.demo61_account_thymeleaf.repository.CategoryRepository;
import com.example.demo61_account_thymeleaf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo61_account_thymeleaf.service.AccountService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AccountController {

    private AccountService accountService;

    private AccountRepository accountRepo;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public AccountController(AccountService accountService,
                             AccountRepository accountRepo) {
        this.accountService = accountService;
        this.accountRepo = accountRepo;
    }
    @GetMapping
    public String clientIndex(Model model) {
        String page = "admin-index";
        model.addAttribute("page",page);
        return "admin-index";
    }

    @GetMapping("/account")
    public String getAll(Model model) {
        String page = "account-list";
        List<Account> accounts = accountService.getAll();
        model.addAttribute("accounts", accounts);
        model.addAttribute("page",page);

        return "admin-index";
    }
    @GetMapping("/category")
    public String getAllCategory(Model model) {
        String page = "admin-category";
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categorys", categories);
        model.addAttribute("page",page);

        return "admin-index";
    }    @GetMapping("/product")
    public String getAllProduct(Model model) {
        String page = "admin-product";
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("page",page);

        return "admin-index";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Account> opAccount = accountRepo.findById(id);
        if (opAccount.isEmpty()) {
            System.out.println("Not found Account with id = " + id);
        }
        accountRepo.deleteById(id);
        return "redirect:/account";
    }

    @GetMapping("delete/product/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            System.out.println("Not found product with id = " + id);
        }
        productRepository.deleteById(id);
        return "redirect:/product";
    }
    @GetMapping("delete/category/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            System.out.println("Not found category with id = " + id);
        }
        categoryRepository.deleteById(id);
        return "redirect:/product";
    }
}
