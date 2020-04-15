package com.csc510.smartweather.dto;

import com.csc510.smartweather.model.Seller;
import lombok.Data;

/**
 * @author Heng Yu
 * @date 4/12/20 9:02 PM
 */

@Data
public class AdvertisementDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer sellerId;
    private Integer sellerCity;
    private Long createdAt;
    private Long updatedAt;
    private Seller seller;
}
