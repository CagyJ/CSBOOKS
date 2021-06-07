package com.cagyj.books.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cagyj.books.entity.Book;
import com.cagyj.books.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceImplTest {

    @Resource
    private BookService bookService;

    @Test
    public void paging() {
        IPage<Book> paging = bookService.paging(2, 4);
        // 获取具体值集合
        List<Book> list = paging.getRecords();
        list.stream().forEach(System.out::println);
        // 总页数
        System.out.println(paging.getPages());
        // 总记录数
        System.out.println(paging.getTotal());
    }
}