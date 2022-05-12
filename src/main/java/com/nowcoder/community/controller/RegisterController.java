package com.nowcoder.community.controller;

import com.nowcoder.community.constants.CommonsConstants;
import com.nowcoder.community.entity.domain.User;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "register", method = RequestMethod.GET)
    public String registerPage() {
        return "site/register";
    }

    @RequestMapping(path = "register", method = RequestMethod.POST)
    public String registerAccount(Model model, User user) {
        final Map<String, Object> res = userService.register(user);
        if (res.containsKey("success")) {
            model.addAttribute("Msg", "注册成功，我们已经向你发送了一份邮件，请尽快激活!");
            model.addAttribute("target", "/index");
            return "site/operate-result";
        } else {
            model.addAttribute("usernameMsg",res.get("usernameMsg"));
            model.addAttribute("passwordMsg", res.get("passwordMsg"));
            model.addAttribute("emailMsg", res.get("emailMsg"));
            return "site/register";
        }
    }
    // String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
    @GetMapping("activation/{userId}/{code}")
    public String activeAccount(
            @PathVariable("userId") Integer userId, @PathVariable("code") String code,Model model) {
        final String res = userService.active(userId, code);
        if (res.equals(CommonsConstants.ACTIVATION_ERROR)) {
            model.addAttribute("Msg", "激活失败");

        } else if (res.equals(CommonsConstants.ACTIVATION_REPEAT)) {
            model.addAttribute("Msg", "无需重复激活");
        } else {
            model.addAttribute("Msg", "激活成功 ");
        }
        model.addAttribute("target", "/index");
        return "site/operate-result";
    }

}
