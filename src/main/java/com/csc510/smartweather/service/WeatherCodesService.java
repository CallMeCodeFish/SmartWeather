package com.csc510.smartweather.service;

import com.csc510.smartweather.mapper.RecommendationsMapper;
import com.csc510.smartweather.mapper.WeatherCodesMapper;
import com.csc510.smartweather.model.Recommendation;
import com.csc510.smartweather.model.WeatherCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherCodesService {
    @Autowired
    private WeatherCodesMapper weatherCodesMapper;

    public WeatherCode getWeatherCode(int weather_code) {
        return weatherCodesMapper.selectByWeatherCode(weather_code);
    }
}
