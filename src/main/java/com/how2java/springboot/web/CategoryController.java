package com.how2java.springboot.web;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    private CategoryDAO categoryDAO;

	@Autowired
    private ProductMapper productMapper;

    @RequestMapping("/listCategory")
    public String listCategory(Model m, //在参数里接受当前是第几页 start ，以及每页显示多少条数据 size。 默认值分别是0和5
                               @RequestParam(value = "start",defaultValue = "0") int start,
                               @RequestParam(value = "size",defaultValue = "5") int size) throws Exception {
        start = start<0? 0: start; //如果 start 为负，那么修改为0. 这个事情会发生在当前是首页，并点击了上一页的时候
        Sort sort = new Sort(Sort.Direction.DESC, "id"); //设置id倒排序
        Pageable pageable = new PageRequest(start, size, sort); //根据start,size和sort创建分页对象
        Page<Category> page = categoryDAO.findAll(pageable); //CategoryDAO根据这个分页对象获取结果page;
        m.addAttribute("page", page); //把page扔进model,放在"page"属性里
        // 在这个page对象里，不仅包含了分页信息，还包含了数据信息，即有哪些分类数据。 这个可以通过getContent()获取出来
        return "listCategory";
    }

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

    @RequestMapping("/editCategory")
    public String editCategory(int id,Model m) throws Exception {
        Category c = categoryDAO.getOne(id);
        m.addAttribute("c", c);
        return "editCategory";
    }

    @RequestMapping("/updateCategory")
    public String updateCategory(Category c) throws Exception {
        categoryDAO.save(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/listProduct")
    public String listProduct(Model m, //在参数里接受当前是第几页 start ，以及每页显示多少条数据 size。 默认值分别是0和5。
                              @RequestParam(value = "start", defaultValue = "0") int start,
                              @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start, size, "id desc"); //根据start,size进行分页，并且设置id 倒排序
        List<Product> ps = productMapper.findAll(); //因为PageHelper的作用，这里就会返回当前分页的集合了
        PageInfo<Product> page = new PageInfo<>(ps); //根据返回的集合，创建PageInfo对象
        m.addAttribute("page", page); // 把PageInfo对象扔进model，以供后续显示
        return "listProduct";
    }

    @RequestMapping("/addProduct")
    public String addProduct(Product p) throws Exception {
        productMapper.save(p);
        return "redirect:listProduct";
    }

    @RequestMapping("/deleteProduct")
    public String deleteProduct(Product p) throws Exception {
        productMapper.delete(p.getId());
        return "redirect:listProduct";
    }

    @RequestMapping("/editProduct")
    public String editProduct(int id,Model m) throws Exception {
        Product p = productMapper.get(id);
        m.addAttribute("p", p);
        return "editProduct";
    }

    @RequestMapping("/updateProduct")
    public String updateProduct(Product p) throws Exception {
        productMapper.update(p);
        return "redirect:listProduct";
    }

    @RequestMapping("/uploadPage")
    public String uploadPage() {
        return "uploadPage";
    }
}
