package com.csc510.smartweather.controller;

import com.csc510.smartweather.service.RecommendationsService;
import com.csc510.smartweather.service.WeatherCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        int weather_code = 731;
        model.addAttribute("weather_codes", weatherCodesService.getWeatherCode(weather_code));
        model.addAttribute("recommendations", recommendationsService.getRecommendations(weather_code));
        return "index";
    }

    @GetMapping("/sign-out")
    public String signOut(HttpServletRequest request, HttpServletResponse response) {
        //处理异常

        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("sw-token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
