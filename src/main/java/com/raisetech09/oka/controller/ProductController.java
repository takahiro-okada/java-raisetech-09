package com.raisetech09.oka.controller;

import com.raisetech09.oka.service.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.raisetech09.oka.repository.ProductMapper;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    private ProductMapper productMapper;

    @Autowired
    public ProductController(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @GetMapping("/product")
    public List<Product> get() {
        return productMapper.findAll();
    }
}
