package com.example.demo61_account_thymeleaf.controller;

import com.example.demo61_account_thymeleaf.dto.ProductDTO;
import com.example.demo61_account_thymeleaf.entity.Account;
import com.example.demo61_account_thymeleaf.entity.Category;
import com.example.demo61_account_thymeleaf.entity.Product;
import com.example.demo61_account_thymeleaf.repository.AccountRepository;
import com.example.demo61_account_thymeleaf.repository.CategoryRepository;
import com.example.demo61_account_thymeleaf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo61_account_thymeleaf.service.AccountService;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product obj : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(obj.getId());
            productDTO.setName(obj.getName());
            productDTO.setImagePath(obj.getImagePath());
            productDTO.setPrice(obj.getPrice());

            if (obj.getCategory() !=null ){
                productDTO.setCategoryName(obj.getCategory().getName());
            }
            productDTOS.add(productDTO);
            System.out.println(productDTOS);
        }

        model.addAttribute("products", productDTOS);
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
    @GetMapping("product-add")
    public String addProduct(Model model) {
        model.addAttribute("page","admin-product-add");
        return "admin-index";
    }
    @PostMapping("product-save")
    public String saveProduct(@RequestParam String productName,
                              @RequestParam int price,
                              @RequestParam MultipartFile image) {
        System.out.println(productName);
        String fileName = image.getOriginalFilename();
        Product product = new Product();
        product.setPrice(price);
        product.setName(productName);
        product.setImagePath(fileName);
        productRepository.save(product);
        System.out.println(image);
        return "redirect:/admin/product";
    }

    @GetMapping("delete/product/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            System.out.println("Not found product with id = " + id);
        }
        productRepository.deleteById(id);
        return "redirect:/admin/product";
    }

    @GetMapping("/product-edit/{id}")
    public String editProduct(@PathVariable Integer id,Model model) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            System.out.println("Not found product with id = " + id);
        }

        Product product = optionalProduct.get();
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("page","admin-product-edit");
        model.addAttribute("product",product);
        model.addAttribute("categories",categories);

        return "admin-index";
    }
    @PostMapping("/product-update")
    public String updateProduct(@ModelAttribute Product updatedProduct) {
        // Lưu thông tin sản phẩm đã được cập nhật vào cơ sở dữ liệu
        Product existingProduct = productRepository.findById(updatedProduct.getId()).orElse(null);

        if (existingProduct == null) {
            // Xử lý trường hợp sản phẩm không tồn tại
            System.out.println("Product not found with ID: " + updatedProduct.getId());
            // Chuyển hướng hoặc trả về trang lỗi
            return "error"; // Thay "error" bằng tên của trang lỗi hoặc trang thông báo lỗi của bạn
        }

        // Cập nhật thông tin sản phẩm
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        // Lưu vào cơ sở dữ liệu
        productRepository.save(existingProduct);

        // Chuyển hướng sau khi cập nhật thành công
        return "redirect:/admin/product-edit/" + existingProduct.getId(); // Chuyển hướng đến trang chỉnh sửa sản phẩm sau khi cập nhật
    }

    @GetMapping("delete/category/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            System.out.println("Not found category with id = " + id);
        }
        categoryRepository.deleteById(id);
        return "redirect:/admin/category";
    }
}
