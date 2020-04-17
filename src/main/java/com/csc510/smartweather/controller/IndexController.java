package com.csc510.smartweather.controller;

import com.csc510.smartweather.service.RecommendationsService;
import com.csc510.smartweather.service.WeatherCodesService;
import com.csc510.smartweather.utilities.RequestsHandler;
import com.csc510.smartweather.utilities.Utils;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    private int weather_code = 731;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("weather_codes", weatherCodesService.getWeatherCode(weather_code));
        model.addAttribute("recommendations", recommendationsService.getRecommendations(weather_code));
        return "index";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam(value = "searchStr") String searchStr) {
        model.addAttribute("weather_codes", weatherCodesService.getWeatherCode(weather_code));
        model.addAttribute("recommendations", recommendationsService.getRecommendations(weather_code));
        Map<String, String> params = new HashMap<>();
        params.put("address", searchStr);
        params.put("key", "AIzaSyC3yQajtK6rZvpmZPvt-i3cqW6_6nSjBtA");
        JSONObject locationJSON = RequestsHandler.getRequestJSON("https://maps.googleapis.com/maps/api/geocode/json", params);
        float[] latlong = new float[] {0, 0};
        if (locationJSON != null)
            latlong = Utils.getLatLongFromJSON(locationJSON);
        model.addAttribute("latitude", latlong[0]);
        model.addAttribute("longitude", latlong[1]);
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
