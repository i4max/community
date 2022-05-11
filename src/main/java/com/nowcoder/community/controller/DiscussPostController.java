package com.nowcoder.community.controller;

import com.nowcoder.community.entity.common.Page;
import com.nowcoder.community.entity.domain.DiscussPost;
import com.nowcoder.community.entity.domain.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@Controller
public class DiscussPostController {
    @Resource
    DiscussPostService discussPostService;

    @Resource
    UserService userService;

    @RequestMapping(path = "index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page, @RequestParam(value = "current", defaultValue = "1") int current) {
        page.setRows(discussPostService.getDiscussPostRows(0));
        page.setUrl("/index");
        page.setCurrent(current);
        List<DiscussPost> discussPostList = discussPostService.getDiscussList(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (discussPostList != null) {
            for (DiscussPost post : discussPostList) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.getUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        return "index";
    }
}
