package com.nowcoder.community.service;

import com.nowcoder.community.constants.CommonsConstants;
import com.nowcoder.community.entity.domain.User;
import com.nowcoder.community.mapper.UserMapper;
import com.nowcoder.community.utils.CommonsUtil;
import com.nowcoder.community.utils.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public Map<String, Object> register(User user) {
        // 判断用户是否非空
        if (user == null) {
            throw new IllegalArgumentException("argument is illegal!");
        }

        Map<String, Object> res = new HashMap<>();

        // 分别判断用户名，密码，邮箱是否非空
        if (StringUtils.isAnyBlank(user.getUsername())) {
            res.put("usernameMsg", "用户名不能为空!");
            return res;
        }

        if(StringUtils.isAnyBlank(user.getPassword())) {
            res.put("passwordMsg", "密码不能为空!");
            return res;
        }

        if (StringUtils.isAnyBlank(user.getEmail())) {
            res.put("emailMsg", "邮箱不能为空!");
            return res;
        }

        //判断用户名是否已经被注册
        User u = userMapper.getUserByName(user.getUsername());
        if (u != null) {
            res.put("usernameMsg", "用户名已经存在!");
            return  res;
        }
        // 判断邮箱是否已经被使用
        u = userMapper.getUserByEmail(user.getEmail());
        if (u != null) {
            res.put("emailMsg", "邮箱已经被注册了!");
            return res;
        }

        // 生成随机的盐，加密密码，给到随机的头像后将用户插入到数据库中
        String salt = CommonsUtil.generateUUID().substring(0,5);
        String cipherText = CommonsUtil.encrypt(user.getPassword() + salt);
        user.setSalt(salt);
        user.setPassword(cipherText);
        user.setStatus(0);
        user.setType(0);
        user.setCreateTime(new Date());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));
        user.setActivationCode(CommonsUtil.generateUUID());
        userMapper.insertUser(user);

        // 构建激活的连接发送激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        final String content = templateEngine.process("mail/activation", context);
//        System.out.println(content);
        mailClient.sendMail(user.getEmail(), "激活账号", content);

        res.put("success", "注册成功，请激活！");
        return res;
    }

    public String active(int userId, String activationCode) {
        User user = userMapper.getUserById(userId);
        if (user == null || !user.getActivationCode().equals(activationCode)) {
            return CommonsConstants.ACTIVATION_ERROR;
        }
        if (user.getStatus().equals(1)) {
            return CommonsConstants.ACTIVATION_REPEAT;
        }
        if (user.getActivationCode().equals(activationCode)) {
            userMapper.updateStatus(userId, 1);
            return CommonsConstants.ACTIVATION_SUCCESS;
        }
        return CommonsConstants.ACTIVATION_ERROR;
    }
}
