package com.raisetech09.oka.repository;

import com.raisetech09.oka.controller.ProductResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM product")
    List<ProductResponse> findAll();

    @Select("SELECT * FROM product WHERE id = #{id}")
    List<ProductResponse> findById(int id);

    @Select("SELECT * FROM product WHERE price = #{price}")
    List<ProductResponse> findByPrice(int price);
}
