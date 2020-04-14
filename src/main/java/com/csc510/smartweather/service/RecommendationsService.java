package com.csc510.smartweather.service;

import com.csc510.smartweather.mapper.RecommendationsMapper;
import com.csc510.smartweather.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecommendationsService {
    @Autowired
    private RecommendationsMapper recommendationsMapper;

    public List<Recommendation> getRecommendations(int weather_code) {
        return recommendationsMapper.selectByWeatherCode(weather_code);
    }
}
