package com.allen.learningbootjpa;

import com.allen.learningbootjpa.pojo.DO.BookDO;
import com.allen.learningbootjpa.pojo.DO.CourseDO;
import com.allen.learningbootjpa.pojo.DO.CourseInterf;
import com.allen.learningbootjpa.repository.BookRepository;
import com.allen.learningbootjpa.repository.CourseRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningBootJpaApplicationTests {
    
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionTemplate transactionTemplate;
    
    @Test
    public void test1() {
        List<CourseDO> all = courseRepository.findAll();
        System.out.println(all);
    }
    
    @Test
    public void test2() {
        List<CourseDO> all = courseRepository.selectWithAnno("语文");
        System.out.println(all);
    }
    
    @Test
    public void test3() {
        List<CourseDO> all = courseRepository.selectWithAnnoNative("语文");
        System.out.println(all);
    }
    
    @Test
    public void test4() {
        List<CourseDO> all = courseRepository.selectWithAnnoNameQuery("语文");
        System.out.println(all);
    }
    
    @Test
    public void test5() {
        Page<CourseDO> page = courseRepository.findByNameLikeOrderByIdDesc("%", PageRequest.of(0, 10));
        System.out.println(page.getTotalElements());  // 总数
        System.out.println(page.getTotalPages());   // 总页数
        System.out.println(page.getNumberOfElements()); // 当前页数据量
        System.out.println(page.getContent());
    }
    
    @Test
    public void test6() {
        Optional<CourseDO> op = courseRepository.findFirstByIdEquals(1);
        System.out.println(op.orElseThrow(() -> new IllegalArgumentException("not found")));
    }
    
    @Test
    public void test7() {
        Optional<CourseDO> op = courseRepository.findFirstByIdEquals(5);
        System.out.println(op.orElseThrow(IllegalArgumentException::new));
    }
    
    @Test
    public void test8() {
        CourseDO add = new CourseDO();
        add.setName("体育");
        CourseDO save = courseRepository.save(add);
        System.out.println(save);
    }
    
    @Test
    public void test9() {
        BookDO add = new BookDO();
        add.setPrice("23.5");
        BookDO save = bookRepository.saveNew(add);
        System.out.println(save);
    }
    
    @Test
    public void test10() {
        Sort sort = Sort.by(Order.desc("name"), Order.asc("id"));
        List<CourseDO> dos = courseRepository.selectWithAnnoSort("语文", sort);
        System.out.println(dos);
    }
    
    @Test
    public void test11() {
        courseRepository.update("国文", 1);
    }
    
    @Test
    public void test12() {
        CourseInterf top = courseRepository.findTopByIdEquals(1);
        System.out.println(top.getName());
    }
    
    @Test
    public void test13() {
        Specification<BookDO> specification = new Specification<BookDO>() {
            public Predicate toPredicate(Root<BookDO> root, CriteriaQuery<?> query,
                CriteriaBuilder builder) {
                return builder.equal(root.get("price"), "20.5");
            }
        };
        List<BookDO> dos = bookRepository.findAll(specification);
        System.out.println(dos);
    }
    
}
