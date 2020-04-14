package com.csc510.smartweather.controller;

import com.csc510.smartweather.service.RecommendationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Heng Yu
 * @date 4/9/20 9:33 PM
 */

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        String weather = "Thunderstorm";
        model.addAttribute("clothingReco", new RecommendationService().getRecommendation(weather, "clothing"));
        model.addAttribute("activityReco", new RecommendationService().getRecommendation(weather, "activity"));
        model.addAttribute("plantReco", new RecommendationService().getRecommendation(weather, "plant"));
        return "index";
    }
}
