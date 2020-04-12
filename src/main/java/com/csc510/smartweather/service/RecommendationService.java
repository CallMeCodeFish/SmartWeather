package com.csc510.smartweather.service;

import com.csc510.smartweather.mapper.RecommendationMapper;
import com.csc510.smartweather.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecommendationService {
    @Autowired
    private RecommendationMapper recommendationMapper;

    public String getRecommendation(String weather, String type) {
        Recommendation reco = recommendationMapper.selectByWeatherAndType(weather, type);
        return reco.getText();
    }
}
