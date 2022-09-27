package com.raisetech09.oka.service;

import com.raisetech09.oka.entity.CreateProduct;
import com.raisetech09.oka.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(int id);

    List<Product> findByPrice(int price);

    void create(CreateProduct createProduct);
}
