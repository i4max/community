package com.nowcoder.community.service;

import com.nowcoder.community.entity.domain.DiscussPost;
import com.nowcoder.community.mapper.DiscussPostMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@Service
public class DiscussPostService {
    @Resource
    DiscussPostMapper discussPostMapper;

    public List<DiscussPost> getDiscussList(int userId, int offset, int limit) {
        return discussPostMapper.getDiscussList(userId, offset, limit);
    }

    public int getDiscussPostRows(int userId) {
        return discussPostMapper.getDiscussPostRows(userId);
    }
}
