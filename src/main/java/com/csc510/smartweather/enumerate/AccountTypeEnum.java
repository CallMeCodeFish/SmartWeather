package com.csc510.smartweather.enumerate;

/**
 * @author Heng Yu
 * @date 4/10/20 7:22 PM
 */


public enum AccountTypeEnum {
    GITHUB(1, "github"),
    GOOGLE(2, "google");

    private Integer type;
    private String description;

    AccountTypeEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
