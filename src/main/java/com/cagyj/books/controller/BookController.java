package com.cagyj.books.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cagyj.books.entity.*;
import com.cagyj.books.service.BookService;
import com.cagyj.books.service.CategoryService;
import com.cagyj.books.service.EvaluationService;
import com.cagyj.books.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private BookService bookService;

    @Resource
    private EvaluationService evaluationService;

    @Resource
    private MemberService memberService;

    /**
     * 显示首页
     * @return
     */
    @GetMapping("/")
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("/index");
        List<Category> list = categoryService.selectAll();
        mav.addObject("categoryList", list);
        return mav;
    }

    /**
     * 分页查询图书
     * @param p
     * @return
     */
    @GetMapping("/books")
    @ResponseBody  // JSON序列化输出
    public IPage<Book> selectBook(Integer p) {
        if (p == null) {
            p = 1;
        }
        IPage<Book> paging = bookService.paging(p, 10);
        return paging;
    }

    @GetMapping("/book/{id}") // 路径变量获取id编号
    public ModelAndView showBook(@PathVariable("id")Long id, HttpSession session) {
        Book book = bookService.selectById(id);
        List<Evaluation> evaluations = evaluationService.selectEvalByBookId(id);
        for (Evaluation e :
                evaluations) {
            Member member = memberService.selectById(e.getMemberId());
            e.setMember(member);
        }

        ModelAndView modelAndView = new ModelAndView("/detail");
        // 查询member阅读状态
        Member member = (Member) session.getAttribute("loginMember");
        if (member != null) {
            MemberReadState memberReadState = memberService.selectMemberReadState(member.getMemberId(), id);
            modelAndView.addObject("memberReadState", memberReadState);
        }

        modelAndView.addObject("book", book);
        modelAndView.addObject("evaluationList", evaluations);
        return modelAndView;
    }
}
