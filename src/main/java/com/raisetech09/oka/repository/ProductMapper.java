package com.raisetech09.oka.repository;

import com.raisetech09.oka.entity.CreateProduct;
import com.raisetech09.oka.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM product")
    List<Product> findAll();

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findById(int id);

    @Select("SELECT * FROM product WHERE price = #{price}")
    List<Product> findByPrice(int price);

    @Select("insert into product (name,price) values (#{name},#{price})")
    void createProduct(CreateProduct createProduct);
}
