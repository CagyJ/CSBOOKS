package com.cagyj.books.controller;

import com.cagyj.books.entity.Evaluation;
import com.cagyj.books.entity.Member;
import com.cagyj.books.service.MemberService;
import com.cagyj.books.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/login.html")
    public ModelAndView showLogin() {
        return new ModelAndView("/login");
    }

    @PostMapping("/check_login")
    @ResponseBody
    public Map checkLogin(String vc, String username, String password, HttpSession session) {
        String verifyCode = (String) session.getAttribute("kaptchaVerifyCode");
        HashMap map = new HashMap();
        // 验证码不存在，或不相等，返回错误
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            map.put("code", "VC01");
            map.put("msg", "Verify Code is wrong");
        } else {
            try {
                Member member = memberService.checkLogin(username, password);
                session.setAttribute("loginMember", member);
                map.put("code", "0");
                map.put("msg", "success");
            } catch (BussinessException exception) {
                exception.printStackTrace();
                map.put("code", exception.getCode());
                map.put("msg", exception.getMsg());
            }
        }
        return map;
    }

    @GetMapping("/register.html")
    public ModelAndView showRegister() {
        ModelAndView mav = new ModelAndView("/register");
        return mav;
    }

    @PostMapping("register")
    @ResponseBody
    public Map register(String vc, String username, String password, String nickname, HttpServletRequest req, HttpServletResponse resp) {
        String verifyCode = (String) req.getSession().getAttribute("kaptchaVerifyCode");
        HashMap map = new HashMap();
        // 验证码不存在，或不相等，返回错误
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            map.put("code", "VC01");
            map.put("msg", "Verify Code is wrong");
        } else {

            try {
                memberService.createMember(username, password, nickname);
                map.put("code", "0");
                map.put("msg", "success");
            } catch (BussinessException exception) {
                map.put("code", "VC01");
                map.put("msg", "This account exits, pls go ahead to login!");
                exception.printStackTrace();
            }
        }
        return map;
    }

    @PostMapping("/update_read_state")
    @ResponseBody
    public Map updateReadState(Long memberId, Long bookId, Integer readState) {
        Map result = new HashMap();
        try {
            memberService.updateMemberReadState(memberId, bookId, readState);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BussinessException ex) {
            ex.printStackTrace();
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
        }
        return result;
    }

    @PostMapping("/evaluate")
    @ResponseBody
    public Map evaluate(Long memberId, Long bookId, Integer score, String content) {
        Map result = new HashMap();
        try {
            memberService.evaluate(memberId, bookId, score, content);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BussinessException ex) {
            ex.printStackTrace();
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
        }
        return result;
    }

    @PostMapping("/enjoy")
    @ResponseBody
    public Map enjoy(Long evaluationId) {
        Map result = new HashMap();
        try {
            Evaluation evaluation = memberService.enjoy(evaluationId);
            result.put("code", "0");
            result.put("msg", "success");
            result.put("evaluation", evaluation);
        } catch (BussinessException ex) {
            ex.printStackTrace();
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
        }
        return result;
    }
}
