package com.cagyj.books.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cagyj.books.entity.Test;

import java.util.List;
import java.util.Map;


public interface TestMapper extends BaseMapper<Test> {
    public void insertSample();
    public List<Map> selectAll();
}
