package com.example.demo61_account_thymeleaf.controller;

import com.example.demo61_account_thymeleaf.dto.ProductDTO;
import com.example.demo61_account_thymeleaf.entity.Product;
import com.example.demo61_account_thymeleaf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("product")
    public String getAll(Model model, Pageable pageable){
        //pageable nhận trang số bao nhiêu sắp xếp theo alphabet hoặc ngược lại, mỗi trang bao nhiêu bản ghi ví dụ=10
        //sắp xếp tăng dần alpha beta
        //mặc định trang sẽ là 1

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
        String page = "product-list-client";
        model.addAttribute("page",page);
        return "client-index";
    }
    @GetMapping("search")
    public String search(Model model,
                         @RequestParam String data){
        List<Product> products = new ArrayList<>();
        if (data.equals("")){
            products = productRepository.findAll();
        }
        model.addAttribute("product",products);
        model.addAttribute("page","product-list-client");
        return "client-index";
    }
}
