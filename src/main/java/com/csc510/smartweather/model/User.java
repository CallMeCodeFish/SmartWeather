package com.csc510.smartweather.model;

import lombok.Data;

/**
 * @author Heng Yu
 * @date 4/10/20 7:15 PM
 */

@Data
public class User {
    private Integer id;
    private String name;
    private Integer accountType;
    private String accountId;
    private String avatarUrl;
    private String token;
    private Long createdAt;
    private Long updatedAt;
}
