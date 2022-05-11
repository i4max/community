package com.nowcoder.community.mapper;

import com.nowcoder.community.entity.domain.User;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@MapperScan
public interface UserMapper {
    /**
     *
     * @param id 传入用户id
     * @return 返回一个用户实体
     */
    User getUserById(@Param("id") Integer id);

    /**
     *
     * @param username 传入用户名
     * @return  返回一个用户实体
     */
    User getUserByName(@Param("username")String username);

    User getUserByEmail(@Param("email")String email);

    Integer insertUser(User user);

    Integer updateHeader(@Param("id")int id, @Param("headerUrl")String headUrl);

    Integer updatePassword(@Param("id")int id, @Param("password")String password);

    Integer updateStatus(@Param("id")int id ,@Param("status")int status);
}
