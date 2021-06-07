package com.cagyj.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cagyj.books.entity.Category;
import com.cagyj.books.mapper.CategoryMapper;
import com.cagyj.books.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService") // bean id为categoryService
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)  // 不使用事务
public class CategoryServiceImpl implements CategoryService {

    // 注入mapper
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * query all categories
     * @return list of categories
     */
    @Override
    public List<Category> selectAll() {
        List<Category> list = categoryMapper.selectList(new QueryWrapper<Category>());
        return list;
    }
}
