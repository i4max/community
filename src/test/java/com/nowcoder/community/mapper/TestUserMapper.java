package com.nowcoder.community.mapper;
import java.util.Date;

import com.nowcoder.community.entity.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@SpringBootTest
public class TestUserMapper {
    @Resource
    UserMapper userMapper;
    @Test
    public void allTest() {
        User user = new User();
        user.setUsername("龙脉龙---渊");
        user.setPassword(DigestUtils.md5DigestAsHex("gaojing,dajiejiangzhi,weiletianxiacangshengxianchunideshengmingba".getBytes()));
        user.setSalt("1234");
        user.setEmail("yuan@chuanwu.com");
        user.setType(1);
        user.setStatus(1);
        user.setActivationCode("");
        user.setHeaderUrl("haimeiyou");
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

    }

}
