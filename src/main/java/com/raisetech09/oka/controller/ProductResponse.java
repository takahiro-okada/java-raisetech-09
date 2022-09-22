package com.raisetech09.oka.controller;

import com.raisetech09.oka.entity.Product;

import java.util.List;

public class ProductResponse {

    private int id;
    private String name;
    private int price;

    public ProductResponse(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductResponse(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
