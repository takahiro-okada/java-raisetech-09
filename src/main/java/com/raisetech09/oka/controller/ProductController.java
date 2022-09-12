package com.raisetech09.oka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.raisetech09.oka.repository.ProductMapper;

@Controller
public class ProductController {
    private ProductMapper productMapper;

    @Autowired
    public ProductController(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @GetMapping("/index")
    public String get(Model model) {
        model.addAttribute("test", productMapper.findAll());
        return "/index";

    }
}
