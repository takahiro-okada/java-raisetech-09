package com.raisetech09.oka.controller;

import com.raisetech09.oka.entity.Product;
import com.raisetech09.oka.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //    localhost:8080/products?price=200のGETリクエストを行い、価格が200で登録されている情報を取得する
    //    もしパラメーターがセットされていなければ商品全件の情報を取得する
    @GetMapping("/products")
    public List<ProductResponse> get(
            @RequestParam(value = "price", defaultValue = "-1") int price
    ) {
        if(price == -1){
            List<Product> products = productService.findAll();
            return products.stream().map(ProductResponse::new).toList();
        } else {
            List<Product> products = productService.findByPrice(price);
            return products.stream().map(ProductResponse::new).toList();
        }
    }


    //  idで検索する
    @GetMapping("/products/{id}")
    public List<ProductResponse> getProductById(
            @PathVariable int id
    ) {
        List<Product> products = productService.findById(id);
        return products.stream().map(ProductResponse::new).toList();
    }

}
