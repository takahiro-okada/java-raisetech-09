package com.raisetech09.oka.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.raisetech09.oka.service.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM product")
    List<Product> findAll();

    @Select("SELECT * FROM product WHERE id = #{id}")
    List<Product> findById(int id);

    @Select("SELECT * FROM product WHERE price = #{price}")
    List<Product> findByPrice(int price);
}