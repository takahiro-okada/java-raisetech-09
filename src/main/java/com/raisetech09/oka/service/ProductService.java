package com.raisetech09.oka.service;

import com.raisetech09.oka.controller.ProductResponse;
import com.raisetech09.oka.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    List<Product> findById(int id);

    List<Product> findByPrice(int price);
}
