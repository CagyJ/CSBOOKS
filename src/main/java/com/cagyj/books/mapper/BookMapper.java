package com.cagyj.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cagyj.books.entity.Book;

public interface BookMapper extends BaseMapper<Book> {

    /**
     * 更新图书评分
     */
    public void updateEvaluation();
}
