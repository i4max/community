package com.nowcoder.community.service;

import com.nowcoder.community.entity.domain.User;
import com.nowcoder.community.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
}
