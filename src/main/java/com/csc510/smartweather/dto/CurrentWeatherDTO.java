package com.csc510.smartweather.dto;

import lombok.Data;

@Data
public class CurrentWeatherDTO {
    private Float temp;
    private Float feel_like;
    private Float temp_min;
    private Float temp_max;
    private Integer pressure;
    private Integer humidity;
    private Integer id;
    private String mainInfo;
    private String icon;
    private Float wind_speed;
    private Integer wind_deg;
    private Integer clouds;
    private String city;

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "temp=" + temp +
                ", feel_like=" + feel_like +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", id=" + id +
                ", mainInfo='" + mainInfo +
                ", wind_speed='" + wind_speed +
                ", wind_deg='" + wind_deg +
                ", clouds='" + clouds +
                ", city='" + city +
                '\'' + '}';
    }
}
