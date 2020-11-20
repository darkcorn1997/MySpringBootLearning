package com.how2java.springboot.dao;

import com.how2java.springboot.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


/**MyBatis**/
@Repository
@Mapper //mybatis的注解，表明这个是一个Mapper;可以去掉，但相应地要在启动类上加上MapperScan("路径")
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
