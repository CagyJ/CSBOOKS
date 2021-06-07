package com.cagyj.books.controller.management;

import com.cagyj.books.entity.Member;
import com.cagyj.books.service.MemberService;
import com.cagyj.books.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/management")
public class ManagementController {

    @Resource
    private MemberService memberService;

    @GetMapping("/index.html")
    public ModelAndView showIndex() {
        return new ModelAndView("/management/index");
    }

    @GetMapping("/")
    public ModelAndView showLogin() {
        return new ModelAndView("/management/login");
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
                if (!member.getUsername().equals("cagyjiao")) {
                    throw new BussinessException("VC01", "Not the admin");
                }
                session.setAttribute("loginAdmin", member);
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

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("loginAdmin");
        return new ModelAndView("/management/login");
    }
}
