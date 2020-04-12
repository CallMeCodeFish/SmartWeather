package com.csc510.smartweather.model;

import lombok.Data;

@Data
public class Recommendation {
    private Integer id;
    private String type;
    private String weather;
    private String text;
}
