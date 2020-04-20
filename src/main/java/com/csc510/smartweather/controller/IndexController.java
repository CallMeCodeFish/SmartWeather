package com.csc510.smartweather.controller;
import com.csc510.smartweather.service.RecommendationsService;
import com.csc510.smartweather.service.WeatherCodesService;
import com.csc510.smartweather.utilities.RequestsHandler;
import com.csc510.smartweather.utilities.Utils;

import net.sf.json.JSONObject;
import com.csc510.smartweather.weatherInfo.QueryWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private QueryWeather queryWeather;
    @Autowired
    private RequestsHandler requestsHandler;

    private int weather_code;
    private final String GOOGLE_API_KEY = "KEY";

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
        }

        //检查当前session是否已定位，向前端传入flag，以确定是否调用javascript定位代码
        Boolean located = (Boolean) request.getSession().getAttribute("located");
        if (located != null && located) {
            model.addAttribute("located",  true);
        } else {
            model.addAttribute("located", false);
        }

        String lat = (String)request.getSession().getAttribute("latitude");
        String lon = (String)request.getSession().getAttribute("longitude");

        if (lat != null && lon != null) {
            model.addAttribute("latitude",lat);
            model.addAttribute("longitude",lon);
            model.addAttribute("currentweather", queryWeather.CurrentWeatherInfo(lat,lon));
            model.addAttribute("weather_forecast", queryWeather.WeatherForecastInfo(lat,lon));
            request.getSession().setAttribute("weatherID",queryWeather.CurrentWeatherInfo(lat,lon).getId());
            weather_code = (Integer)request.getSession().getAttribute("weatherID");
            model.addAttribute("weather_codes", weatherCodesService.getWeatherCode(weather_code));
            model.addAttribute("recommendations", recommendationsService.getRecommendations(weather_code));
            Map<String, String> params = new HashMap<>();
            params.put("latlng", lat+','+lon);
            params.put("key", GOOGLE_API_KEY);
            JSONObject locationJSON = requestsHandler.getRequestJSON("https://maps.googleapis.com/maps/api/geocode/json", params);
            model.addAttribute("city", Utils.getCityFromJSON(locationJSON));
        }

        return "index";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam(value = "searchStr") String searchStr) {
        model.addAttribute("weather_codes", weatherCodesService.getWeatherCode(weather_code));
        model.addAttribute("recommendations", recommendationsService.getRecommendations(weather_code));
        Map<String, String> params = new HashMap<>();
        params.put("address", searchStr);
        params.put("key", GOOGLE_API_KEY);
        JSONObject locationJSON = requestsHandler.getRequestJSON("https://maps.googleapis.com/maps/api/geocode/json", params);
        String[] latlong = new String[] {"", ""};
        if (locationJSON != null)
            latlong = Utils.getLatLongFromJSON(locationJSON);
            model.addAttribute("located",  true);
            model.addAttribute("latitude",latlong[0]);
            model.addAttribute("longitude",latlong[1]);
            model.addAttribute("currentweather", queryWeather.CurrentWeatherInfo(latlong[0],latlong[1]));
            model.addAttribute("weather_forecast", queryWeather.WeatherForecastInfo(latlong[0],latlong[1]));
            weather_code = queryWeather.CurrentWeatherInfo(latlong[0],latlong[1]).getId();
            model.addAttribute("weather_codes", weatherCodesService.getWeatherCode(weather_code));
            model.addAttribute("recommendations", recommendationsService.getRecommendations(weather_code));
            params = new HashMap<>();
            params.put("latlng", latlong[0]+','+latlong[1]);
            params.put("key", GOOGLE_API_KEY);
            locationJSON = requestsHandler.getRequestJSON("https://maps.googleapis.com/maps/api/geocode/json", params);
            model.addAttribute("city", Utils.getCityFromJSON(locationJSON));
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
