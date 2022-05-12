package com.nowcoder.community.controller;

import com.google.code.kaptcha.Producer;
import com.jhlabs.image.ImageUtils;
import com.sun.imageio.plugins.common.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@Controller
public class LoginController {

    @Autowired
    Producer producer;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String loginPage() {
        return "site/login";
    }


    @GetMapping("/kaptcha")
    public void getVerificationCode(HttpServletResponse response, HttpServletRequest request) {
        final String text = producer.createText();
        final BufferedImage image = producer.createImage(text);
        request.getSession().setAttribute("code",text);
        response.setContentType("image/png");
        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(image,"png", os);
        } catch (IOException e) {
            logger.error("验证码图片返回错误:" + e.getMessage());
        }
    }
}
