package com.raisetech09.oka.service;

import com.raisetech09.oka.controller.ProductResponse;
import com.raisetech09.oka.repository.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper){
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponse> findAll() {
        return productMapper.findAll();
    }

    @Override
    public List<ProductResponse> findById(int id) {
        return productMapper.findById(id);
    }

    @Override
    public List<ProductResponse> findByPrice(int price) {
        return productMapper.findByPrice(price);
    }
}
