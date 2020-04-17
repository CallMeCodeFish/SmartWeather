package com.csc510.smartweather.controller;
import com.csc510.smartweather.service.RecommendationsService;
import com.csc510.smartweather.service.WeatherCodesService;
import com.csc510.smartweather.weatherInfo.QueryWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private QueryWeather queryWeather;
    @GetMapping("/")
    public String index(Model model,
                        HttpServletRequest request,
                        @RequestParam(value = "latitude", required = false) String latitude,
                        @RequestParam(value = "longitude", required = false) String longitude) {
        //判断是否从前端收到经纬度，如果收到，标记session为已定位(located)
        if (latitude != null && longitude != null) {
            request.getSession().setAttribute("located", true);
            request.getSession().setAttribute("latitude",latitude);
            request.getSession().setAttribute("longitude",longitude);
//            System.out.println("纬度: " + latitude);
//            System.out.println("经度: " + longitude);
        }

        //检查当前session是否已定位，向前端传入flag，以确定是否调用javascript定位代码
        Boolean located = (Boolean) request.getSession().getAttribute("located");
        if (located != null && located) {
            model.addAttribute("located",  true);
        } else {
            model.addAttribute("located", false);
        }

        request.getSession().setAttribute("weatherID",queryWeather.CurrentWeatherInfo().getId());
        int weather_code = (Integer)request.getSession().getAttribute("weatherID");
        model.addAttribute("weather_codes", weatherCodesService.getWeatherCode(weather_code));
        model.addAttribute("recommendations", recommendationsService.getRecommendations(weather_code));
        model.addAttribute("currentweather", queryWeather.CurrentWeatherInfo());
        model.addAttribute("weather_forecast", queryWeather.WeatherForecastInfo());
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
