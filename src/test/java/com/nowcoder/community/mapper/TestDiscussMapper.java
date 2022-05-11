package com.nowcoder.community.mapper;

import com.nowcoder.community.entity.domain.DiscussPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@SpringBootTest
public class TestDiscussMapper {
    @Autowired
    DiscussPostMapper discussPostMapper;
    @Test
    public void discussPostMapperTest() {
        final List<DiscussPost> discussList = discussPostMapper.getDiscussList(111, 0, 10);
        final int discussPostRows = discussPostMapper.getDiscussPostRows(102);
        for (DiscussPost d : discussList) {
            System.out.println(d);
        }
        System.out.println(discussPostRows);
    }
}
