package com.cagyj.books.service.impl;

import com.cagyj.books.entity.Category;
import com.cagyj.books.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CategoryServiceImplTest {

    @Resource
    private CategoryService categoryService;

    @Test
    public void selectAll() {
        List<Category> list = categoryService.selectAll();
        for (Category category :
                list) {
            System.out.println(category.getCategoryId() + ": " + category.getCategoryName());
        }
    }
}