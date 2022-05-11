package com.nowcoder.community.entity.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String email;

    private Integer type;

    private Integer status;

    private String activationCode;

    private String headerUrl;

    private Date createTime;
}
