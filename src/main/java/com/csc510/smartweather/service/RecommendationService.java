package com.csc510.smartweather.service;

import com.csc510.smartweather.mapper.RecommendationMapper;
import com.csc510.smartweather.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecommendationService {
    @Autowired
    private RecommendationMapper recommendationMapper;

    public List<Recommendation> getRecommendation(String weather) {
        return recommendationMapper.selectByWeather(weather);
    }
}
