package com.raisetech09.oka.service;

import com.raisetech09.oka.entity.CreateProduct;
import com.raisetech09.oka.entity.Product;
import com.raisetech09.oka.repository.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public Product findById(int id) {
        return productMapper.findById(id);
    }

    @Override
    public List<Product> findByPrice(int price) {
        return productMapper.findByPrice(price);
    }

    @Override
    public void create(CreateProduct createProduct) {
        productMapper.createProduct(createProduct);
    }
}
