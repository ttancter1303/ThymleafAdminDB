package com.example.demo61_account_thymeleaf.controller;

import com.example.demo61_account_thymeleaf.dto.ProductDTO;
import com.example.demo61_account_thymeleaf.entity.Product;
import com.example.demo61_account_thymeleaf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("product")
    public String getAll(Model model){

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
}
