import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.how2java.springboot.Application;
import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestJPA {

    @Autowired
    CategoryDAO categoryDAO;

    @Before
    public void before() {
        List<Category> cs = categoryDAO.findAll();
        categoryDAO.delete(cs);

        for (int i=0; i<20; i++) {
            Category c = new Category();
            c.setName("category"+i);
            categoryDAO.save(c);
        }
    }

    @Test
    public void test1() {
        List<Category> cs = categoryDAO.findAll();
        System.out.println("所有的分类信息");
        for (Category c:cs) {
            System.out.println(c);
        }
        System.out.println();
    }

    @Test
    public void test2() {
        System.out.println("查询名称是\"category 1\"的分类");
        List<Category> cs = categoryDAO.findByName("category 1");
        for (Category c : cs) {
            System.out.println(c);
        }
        System.out.println();
    }

    @Test
    public void test3() {
        System.out.println("根据名称模糊查询，id 大于5, 并且名称正排序查询");
        List<Category> cs=  categoryDAO.findByNameLikeAndIdGreaterThanOrderByNameAsc("%3%",5);
        for (Category c : cs) {
            System.out.println(c);
        }
        System.out.println();
    }
}