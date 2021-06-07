package com.cagyj.books.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cagyj.books.entity.Book;
import com.cagyj.books.service.BookService;
import com.cagyj.books.service.exception.BussinessException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/management/book")
public class MBookController {

    @Resource
    private BookService bookService;

    @GetMapping("/index.html")
    public ModelAndView showBook() {
        ModelAndView modelAndView = new ModelAndView("/management/book");
        return modelAndView;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map upload(@RequestParam("img") MultipartFile file, HttpServletRequest req) throws IOException {
        // 获取upload目录
        String uploadPath = req.getServletContext().getResource("/").getPath() + "/upload/";
        String filename = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        file.transferTo(new File(uploadPath+filename+suffix));
        Map result = new HashMap();
        result.put("errno", 0);
        result.put("data", new String[]{"/upload/" + filename + suffix});
        return result;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map createBook(Book book) {
        Map map = new HashMap();
        try {
            book.setEvaluationQuantity(0);
            book.setEvaluationScore(0f);
            // description的第一幅图
            Document document = Jsoup.parse(book.getDescription());
            Element element = document.select("img").first();
            String imgSource = element.attr("src");
            book.setCover(imgSource);
            bookService.createBook(book);
            map.put("code", "0");
            map.put("msg", "success");
        } catch (BussinessException ex) {
            ex.printStackTrace();
            map.put("code", ex.getCode());
            map.put("msg", ex.getMsg());
        }
        return map;
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
        IPage<Book> paging = bookService.paging(page, limit);
        Map result = new HashMap();
        result.put("code", "0");
        result.put("msg", "success");
        result.put("data", paging.getRecords());
        result.put("count", paging.getTotal());
        return result;
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public Map selectById(@PathVariable("id")Long bookId) {
        Book book = bookService.selectById(bookId);
        Map result = new HashMap();
        result.put("code", "0");
        result.put("msg", "success");
        result.put("data", book);
        return result;
    }

    @PostMapping("/update")
    @ResponseBody
    public Map updateBook(Book book) {
        Map result = new HashMap();
        try {
            Book oriBook = bookService.selectById(book.getBookId());
            oriBook.setBookName(book.getBookName());
            oriBook.setAuthor(book.getAuthor());
            oriBook.setSubTitle(book.getSubTitle());
            oriBook.setCategoryId(book.getCategoryId());
            oriBook.setDescription(book.getDescription());
            Document document = Jsoup.parse(book.getDescription());
            String cover = document.select("img").first().attr("src");
            oriBook.setCover(cover);
            bookService.updateBook(oriBook);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BussinessException ex) {
            ex.printStackTrace();
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
        }
        return result;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Map deleteBook(@PathVariable("id") Long bookId) {
        Map result = new HashMap();
        try {
            bookService.deleteBookById(bookId);
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
