package com.raisetech09.oka.controller;

import com.raisetech09.oka.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.raisetech09.oka.repository.ProductMapper;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    private ProductMapper productMapper;

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductResponse> get() {
        return productService.findAll();
    }

    //  idで検索する
    @GetMapping("/products/{id}")
    public List<ProductResponse> getProductById(
            @PathVariable int id
    ) {
        List<ProductResponse> product = productService.findById(id);
        return product;
    }

    //    localhost:8080/products?price=200のGETリクエストを行い、価格が200で登録されている情報を取得する
    @GetMapping("/price-list")
    public List<ProductResponse> getProductInfo(
            @RequestParam(value = "price", defaultValue = "") int price
    ) {

        return productService.findByPrice(price);
    }
}
