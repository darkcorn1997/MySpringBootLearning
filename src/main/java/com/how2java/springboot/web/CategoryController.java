package com.how2java.springboot.web;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.*;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CategoryController {
	@Autowired
    private CategoryDAO categoryDAO;

	@Autowired
    private ProductMapper productMapper;


	/**   Category  restful风格   **/

    @GetMapping("/categories")
    public String listCategory(Model m, //JPA分页查询，在参数里接受当前是第几页 start ，以及每页显示多少条数据 size。 默认值分别是0和5
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

    @PostMapping("/categories") //JPA 新增和修改用的都是save. 它根据实体类的id是否为0来判断是进行增加还是修改
    public String addCategory(Category c) throws Exception {
        categoryDAO.save(c);
        return "redirect:/categories";
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(Category c) throws Exception {
        categoryDAO.delete(c);
        return "redirect:/categories";
    }

    @GetMapping("/categories/{id}")
    public String editCategory(@PathVariable("id") int id,Model m) throws Exception {
        Category c = categoryDAO.getOne(id);
        m.addAttribute("c", c);
        return "editCategory";
    }

    @PutMapping("/categories/{id}")
    public String updateCategory(Category c) throws Exception {
        categoryDAO.save(c);
        return "redirect:/categories";
    }


/**   Product  非restful风格   **/

    @RequestMapping("/listProduct")
    public String listProduct(Model m, //mybatis分页查询，在参数里接受当前是第几页 start ，以及每页显示多少条数据 size。 默认值分别是0和5。
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


    /**   上传文件   **/

    @RequestMapping("/uploadPage")
    public String uploadPage() {
        return "uploadPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest req, @RequestParam("file") MultipartFile file, Model m) {
        //接受上传的文件
        try {
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            //根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            String destFileName = req.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;
            //通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名

            File destFile = new File(destFileName); //第一次运行的时候，文件所在的目录往往是不存在的，需要创建一下目录
            destFile.getParentFile().mkdirs();
            file.transferTo(destFile); //把浏览器上传的文件复制到希望的位置

            m.addAttribute("fileName", fileName); //把文件名放在model里，以便后续显示用
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "" + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "" + e.getMessage();
        }
        return "showImg";
    }
}
