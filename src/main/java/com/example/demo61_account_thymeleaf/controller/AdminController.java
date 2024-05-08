//package com.example.demo61_account_thymeleaf.controller;
//
//import com.example.demo61_account_thymeleaf.entity.Account;
//import com.example.demo61_account_thymeleaf.entity.Category;
//import com.example.demo61_account_thymeleaf.entity.Product;
//import com.example.demo61_account_thymeleaf.repository.CategoryRepository;
//import com.example.demo61_account_thymeleaf.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    @Autowired
//    ProductRepository productRepository;
//    @Autowired
//    CategoryRepository categoryRepository;
//    @GetMapping("/category")
//    public String getAllCategory(Model model) {
//        String page = "category-list";
//        List<Category> categories = categoryRepository.findAll();
//        model.addAttribute("categorys", categories);
//        model.addAttribute("page",page);
//
//        return "admin-index";
//    }    @GetMapping("/product")
//    public String getAllProduct(Model model) {
//        String page = "product-list";
//        List<Product> products = productRepository.findAll();
//        model.addAttribute("products", products);
//        model.addAttribute("page",page);
//
//        return "admin-index";
//    }
//
//
//    @DeleteMapping("delete/product/{id}")
//    public String deleteProduct(@PathVariable Integer id) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if (optionalProduct.isEmpty()) {
//            System.out.println("Not found product with id = " + id);
//        }
//        productRepository.deleteById(id);
//        return "redirect:/product";
//    }
//    @DeleteMapping("delete/category/{id}")
//    public String deleteCategory(@PathVariable Integer id) {
//        Optional<Category> categoryOptional = categoryRepository.findById(id);
//        if (categoryOptional.isEmpty()) {
//            System.out.println("Not found category with id = " + id);
//        }
//        categoryRepository.deleteById(id);
//        return "redirect:/product";
//    }
//}
