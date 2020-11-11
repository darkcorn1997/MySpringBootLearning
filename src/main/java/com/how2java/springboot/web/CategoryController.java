package com.how2java.springboot.web;
import java.util.List;
import java.util.SortedMap;

import com.how2java.springboot.dao.ProductMapper;
import com.how2java.springboot.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
	@Autowired
    CategoryDAO categoryDAO;

	@Autowired
	ProductMapper productMapper;

    @RequestMapping("/addCategory") //JPA 新增和修改用的都是save. 它根据实体类的id是否为0来判断是进行增加还是修改
    public String addCategory(Category c) throws Exception {
        categoryDAO.save(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/deleteCategory")
    public String deleteCategory(Category c) throws Exception {
        categoryDAO.delete(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/updateCategory")
    public String updateCategory(Category c) throws Exception {
        categoryDAO.save(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/editCategory")
    public String editCategory(int id,Model m) throws Exception {
        Category c = categoryDAO.getOne(id);
        m.addAttribute("c", c);
        return "editCategory";
    }

    @RequestMapping("/listCategory")
    public String listCategory(Model m,
                               @RequestParam(value = "start",defaultValue = "0") int start,
                               @RequestParam(value = "size",defaultValue = "5") int size) throws Exception {
        start = start<0 ?0: start;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Category> page = categoryDAO.findAll(pageable);

        System.out.println(page.getNumber());
        System.out.println(page.getNumberOfElements());
        System.out.println(page.getSize());
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());

        m.addAttribute("page", page);
        return "listCategory";
    }

    @RequestMapping("/listProduct")
    public String listProduct(Model m) throws Exception {
        List<Product> ps = productMapper.findAll();
        m.addAttribute("cs", ps);
        return "listCategory";
    }
}
