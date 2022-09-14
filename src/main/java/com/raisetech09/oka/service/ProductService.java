package com.raisetech09.oka.service;

import com.raisetech09.oka.controller.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();

    List<ProductResponse> findById(int id);

    List<ProductResponse> findByPrice(int price);
}
