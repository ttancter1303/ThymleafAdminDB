package com.example.demo61_account_thymeleaf.controller;

import com.example.demo61_account_thymeleaf.entity.Account;
import com.example.demo61_account_thymeleaf.entity.Blog;
import com.example.demo61_account_thymeleaf.entity.Product;
import com.example.demo61_account_thymeleaf.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogRepository blogRepository;
    @GetMapping()
    public String getAll(Model model){
        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs",blogs);
        String page = "blog-list-client";
        model.addAttribute("page",page);
        return "client-index";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isEmpty()) {
            System.out.println("Not found Blog with id = " + id);
        }
        blogRepository.deleteById(id);
        return "redirect:/account";
    }
    @GetMapping("blog-add")
    public String addProduct(Model model) {
        model.addAttribute("page","client-blog-add");
        return "client-index";
    }
    @PostMapping("blog-save")
    public String saveBlog(@RequestParam String name,
                              @RequestParam String content,
                              @RequestParam String author) {
        System.out.println(name);
        Blog blog = new Blog();
        blog.setName(name);
        blog.setContent(content);
        blog.setAuthor(author);
        blogRepository.save(blog);
        return "redirect:/blog";
    }
}
