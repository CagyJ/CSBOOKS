package com.cagyj.books;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cagyj.books.entity.Test;
import com.cagyj.books.mapper.TestMapper;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MPTest {

    @Resource
    private TestMapper testMapper;

    @org.junit.Test
    public void testInsert() {
        Test test = new Test();
        test.setName("Pp");
        // Base Mapper中的insert方法
        testMapper.insert(test);
    }

    @org.junit.Test
    public void testUpdate() {
        Test test = testMapper.selectById(10);
        test.setName("Micheal");
        testMapper.updateById(test);
    }

    @org.junit.Test
    public void testDelete() {
        testMapper.deleteById(10);
    }

    @org.junit.Test
    public void testSelectEq() {
        var testQueryWrapper = new QueryWrapper<Test>();
        testQueryWrapper.eq("id", 2);
        List<Test> list = testMapper.selectList(testQueryWrapper);
        System.out.println(list.get(0));
    }

    @org.junit.Test
    public void testSelectGt() {
        var testQueryWrapper = new QueryWrapper<Test>();
        testQueryWrapper.gt("id", 2);
        List<Test> list = testMapper.selectList(testQueryWrapper);
        list.stream().forEach(System.out::println);
    }
}
