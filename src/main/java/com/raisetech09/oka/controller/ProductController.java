package com.raisetech09.oka.controller;

import com.raisetech09.oka.service.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.raisetech09.oka.repository.ProductMapper;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    private ProductMapper productMapper;

    @Autowired
    public ProductController(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @GetMapping("/products")
    public List<Product> get() {
        return productMapper.findAll();
    }

    //  idで検索する
    @GetMapping("/products/{id}")
    public  List<Product> getProductById(
            @PathVariable int id
    ){
        List<Product> product = productMapper.findById(id);
        return product;
    }

    //    localhost:8080/products?price=200のGETリクエストを行い、価格が200で登録されている情報を取得する
    @GetMapping("/price-list")
    public List<Product> getProductInfo(
            @RequestParam(value = "price", defaultValue = "") int price
    ) {

        return productMapper.findByPrice(price);
    }
}
