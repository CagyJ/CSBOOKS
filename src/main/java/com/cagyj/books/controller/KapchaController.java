package com.cagyj.books.controller;


import com.google.code.kaptcha.Producer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class KapchaController {

    @Resource
    private Producer kaptchaProducer;

    @GetMapping("/verify_code")
    public void generateVerifyCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        响应时间，立即过期
        resp.setDateHeader("Expires", 0);
//        清除缓存
        resp.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        resp.setHeader("Cache-Control", "post-check=0,pre-check=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setContentType("image/png");
        // 生成验证码字符文本
        String verifyCode = kaptchaProducer.createText();
        req.getSession().setAttribute("kaptchaVerifyCode", verifyCode);
        // 生成验证码图片
        BufferedImage image = kaptchaProducer.createImage(verifyCode);
        ServletOutputStream outputStream = resp.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
