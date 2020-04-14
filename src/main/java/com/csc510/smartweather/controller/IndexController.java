package com.csc510.smartweather.controller;

import com.csc510.smartweather.service.RecommendationsService;
import com.csc510.smartweather.service.WeatherCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Heng Yu
 * @date 4/9/20 9:33 PM
 */

@Controller
public class IndexController {
    @Autowired
    private RecommendationsService recommendationsService;
    @Autowired
    private WeatherCodesService weatherCodesService;
    @GetMapping("/")
    public String index(Model model) {
        int weather_code = 800;
        model.addAttribute("weather_codes", weatherCodesService.getWeatherCode(weather_code));
        model.addAttribute("recommendations", recommendationsService.getRecommendations(weather_code));
        return "index";
    }
}
