package com.nowcoder.community.mapper;

import com.nowcoder.community.entity.domain.DiscussPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@Mapper
public interface DiscussPostMapper {
    /**
     *
     * @param userId 用户后续构建特定用户的帖子查询
     * @param offset  查询页的开始行
     * @param limit 每一页的数据条数
     * @return  返回当前用户/首页的帖子集合
     */
    List<DiscussPost> getDiscussList(int userId, int offset, int limit);

    int getDiscussPostRows(int userId);
}
