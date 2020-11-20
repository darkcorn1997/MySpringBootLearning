package com.how2java.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.springboot.pojo.Category;

import java.util.List;

/**JPA**/
public interface CategoryDAO extends JpaRepository<Category,Integer>{

    List<Category> findByName(String name);
    List<Category> findByNameLikeAndIdGreaterThanOrderByNameAsc(String name, int id);

}
