package com.cagyj.books.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cagyj.books.entity.Book;

public interface BookService {
    /**
     * 分页查询图书
     * @param page
     * @param rows
     * @return
     */
    public IPage<Book> paging(Integer page, Integer rows);

    /**
     * 根据id查询图书
     * @param bookId
     * @return
     */
    public Book selectById(Long bookId);

    /**
     * 更新图书评分
     */
    public void updateEvaluation();

    /**
     * 创建新图书
     * @param book
     * @return
     */
    public Book createBook(Book book);

    /**
     * 更新图书内容
     * @param book
     * @return
     */
    public Book updateBook(Book book);

    /**
     * 删除图书
     * @param bookId
     * @return
     */
    public Book deleteBookById(Long bookId);
}
