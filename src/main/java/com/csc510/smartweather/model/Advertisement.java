package com.csc510.smartweather.model;

import lombok.Data;

/**
 * @author Heng Yu
 * @date 4/12/20 5:28 PM
 */

@Data
public class Advertisement {
    private Integer id;
    private String title;
    private String description;
    private Integer sellerId;
    private String sellerCity;
    private Long createdAt;
    private Long UpdatedAt;
}
