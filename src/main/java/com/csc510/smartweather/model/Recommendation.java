package com.csc510.smartweather.model;

import lombok.Data;

@Data
public class Recommendation {
    private Integer reco_id;
    private String type;
    private String text;
}
