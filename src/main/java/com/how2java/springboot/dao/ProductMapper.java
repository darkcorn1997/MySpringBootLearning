package com.how2java.springboot.dao;

import com.how2java.springboot.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProductMapper {

    @Select("select * from product_")
    List<Product> findAll();

    @Insert("insert into product_ (name, price) values (#{name}, #{price})")
    void save(Product product);

    @Delete("delete from product_ where id = #{id}")
    void delete(int id);

    @Select("select * from product_ where id = #{id}")
    Product get(int id);

    @Update("update product_ set name = #{name} , price = #{price} where id = #{id}")
    void update(Product product);
}
