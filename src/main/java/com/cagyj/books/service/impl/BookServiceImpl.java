package com.cagyj.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cagyj.books.entity.Book;
import com.cagyj.books.entity.Evaluation;
import com.cagyj.books.entity.MemberReadState;
import com.cagyj.books.mapper.BookMapper;
import com.cagyj.books.mapper.EvaluationMapper;
import com.cagyj.books.mapper.MemberReadStateMapper;
import com.cagyj.books.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("bookService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true) // 不开启事务
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;
    @Resource
    private MemberReadStateMapper memberReadStateMapper;
    @Resource
    private EvaluationMapper evaluationMapper;

    @Override
    public IPage<Book> paging(Integer page, Integer rows) {
        Page<Book> p = new Page<Book>(page, rows);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        // 第一个参数是Page对象，第二个是QueryWrapper
        Page<Book> bookPage = bookMapper.selectPage(p, queryWrapper);
        return bookPage;
    }

    @Override
    public Book selectById(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        return book;
    }

    @Override
    @Transactional
    public void updateEvaluation() {
        bookMapper.updateEvaluation();
    }

    @Override
    @Transactional
    public Book createBook(Book book) {
        bookMapper.insert(book);
        return book;
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        bookMapper.updateById(book);
        return book;
    }

    @Override
    @Transactional
    public Book deleteBookById(Long bookId) {
        bookMapper.deleteById(bookId);
        QueryWrapper<MemberReadState> rsQueryWrapper = new QueryWrapper<>();
        rsQueryWrapper.eq("book_id", bookId);
        memberReadStateMapper.delete(rsQueryWrapper);
        QueryWrapper<Evaluation> evaQueryWrapper = new QueryWrapper<>();
        evaQueryWrapper.eq("book_id", bookId);
        evaluationMapper.delete(evaQueryWrapper);
        return null;
    }
}
