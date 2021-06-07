package com.cagyj.books.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cagyj.books.entity.Book;
import com.cagyj.books.entity.Evaluation;
import com.cagyj.books.service.BookService;
import com.cagyj.books.service.EvaluationService;
import com.cagyj.books.service.MemberService;
import com.cagyj.books.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/management/comments")
public class MCommentsController {

    @Resource
    private EvaluationService evaluationService;
    @Resource
    private BookService bookService;
    @Resource
    private MemberService memberService;

    @GetMapping("/index.html")
    public ModelAndView showComments() {
        return new ModelAndView("/management/comments");
    }

    @GetMapping("/list")
    @ResponseBody
    public Map list(Integer page, Integer limit) {
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 10;
        }
        IPage<Evaluation> paging = evaluationService.paging(page, limit);
        List<Evaluation> list = paging.getRecords();
        for (Evaluation e: list) {
            e.setBook(bookService.selectById(e.getBookId()));
            e.setMember(memberService.selectById(e.getMemberId()));
        }
        paging.setRecords(list);
        Map result = new HashMap();
        result.put("code", "0");
        result.put("msg", "success");
        result.put("data", paging.getRecords());
        result.put("count", paging.getTotal());
        return result;
    }

    @PostMapping("/disable")
    @ResponseBody
    public Map disable(Long evaluationId, String reason) {
        Map result = new HashMap();
        try {
            evaluationService.updateById(evaluationId, "disable", reason);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BussinessException ex) {
            ex.printStackTrace();
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
        }
        return result;
    }
}
