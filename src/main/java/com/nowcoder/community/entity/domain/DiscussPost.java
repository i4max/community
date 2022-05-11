package com.nowcoder.community.entity.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@Data
public class DiscussPost {
    private Integer id;

    private Integer userId;

    private String title;

    private String content;

    private Integer type;

    private Integer status;

    private Date createTime;

    private Integer commentCount;

    private Double score;
}
