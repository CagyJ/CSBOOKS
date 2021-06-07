package com.cagyj.books.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping("test/fm")
    public ModelAndView testFreeMarker() {
        return new ModelAndView("/testfreemarker");
    }

    @GetMapping("test/json")
    @ResponseBody
    public Map testJson() {
        Map map = new HashMap();
        map.put("test", "test json series");
        return map;
    }

    @GetMapping("test/wangeditor")
    public ModelAndView testWangeditor() {
        ModelAndView modelAndView = new ModelAndView("/test");
        return modelAndView;
    }
}
