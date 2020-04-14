package com.csc510.smartweather.model;

import lombok.Data;

/**
 * @author Heng Yu
 * @date 4/12/20 1:53 AM
 */

@Data
public class Seller {
    private Integer id;
    private Integer userId;
    private String name;
    private String type;
    private String street;
    private String city;
    private String state;
    private String country;
    private String description;
    private Long createdAt;
    private Long updatedAt;
}
