package com.how2java.springboot.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.dao.ProductMapper;
import com.how2java.springboot.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/products")
    public List<Product> productList(@RequestParam(value = "start", defaultValue = "0") int start,
                                     @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start, size, "id desc");
        List<Product> products = productMapper.findAll();
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return pageInfo.getList();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int id) throws Exception {
        System.out.println(productMapper.get(id));
        return productMapper.get(id);
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) throws Exception {
        productMapper.save(product);
        System.out.println("springboot接收到浏览器以JSON格式提交的数据:" + product);
    }
}