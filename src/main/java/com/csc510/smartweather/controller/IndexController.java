package com.csc510.smartweather.controller;

import com.csc510.smartweather.service.RecommendationService;
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
    private RecommendationService recommendationService;
    @GetMapping("/")
    public String index(Model model) {
        String weather = "Thunderstorm";
        model.addAttribute("recommendations", recommendationService.getRecommendation(weather));
        return "index";
    }
}
