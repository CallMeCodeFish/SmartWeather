package com.csc510.smartweather.provider;

import com.csc510.smartweather.dto.CurrentWeatherDTO;
import com.csc510.smartweather.dto.WeatherForecastDTO;
import com.csc510.smartweather.utilities.RequestsHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class OpenWeatherProvider {
    @Autowired
    private RequestsHandler requestsHandler;

    public CurrentWeatherDTO CurrentWeatherInfo(String lat, String lon) {
        CurrentWeatherDTO currentWeatherDTO = new CurrentWeatherDTO();
        String apiURL = "http://api.openweathermap.org/data/2.5/weather";
        Map<String, String> params = new HashMap<>();
        params.put("lat", lat);
        params.put("lon", lon);
        params.put("units", "imperial");
        params.put("appid", "b6737b31bfa691de6ae7dcffb8f9a49c");
        JSONObject str = requestsHandler.getRequestJSON(apiURL, params);
        JSONObject str1 = JSONObject.fromObject(str.get("main"));
        JSONArray str2 = JSONArray.fromObject(str.get("weather"));
        JSONObject str3 = JSONObject.fromObject(str.get("wind"));
        JSONObject str4 = JSONObject.fromObject(str.get("clouds"));

        currentWeatherDTO.setFeel_like(Float.parseFloat(str1.getString("feels_like")));
        currentWeatherDTO.setHumidity(str1.getInt("humidity"));
        currentWeatherDTO.setPressure(str1.getInt("pressure"));
        currentWeatherDTO.setTemp(Float.parseFloat(str1.getString("temp")));
        currentWeatherDTO.setTemp_min(Float.parseFloat(str1.getString("temp_min")));
        currentWeatherDTO.setTemp_max(Float.parseFloat(str1.getString("temp_max")));
        currentWeatherDTO.setId(str2.getJSONObject(0).getInt("id"));
        currentWeatherDTO.setMainInfo(str2.getJSONObject(0).getString("main"));
        currentWeatherDTO.setIcon(str2.getJSONObject(0).getString("icon"));
        currentWeatherDTO.setWind_speed(Float.parseFloat(str3.getString("speed")));
        if (str3.has("deg")) {
            currentWeatherDTO.setWind_deg(str3.getInt("deg"));
        }
        currentWeatherDTO.setClouds(str4.getInt("all"));
        currentWeatherDTO.setCity(str.getString("name"));
        return currentWeatherDTO;
    }

    public WeatherForecastDTO WeatherForecastInfo(String lat, String lon) {
        WeatherForecastDTO weatherForecastDTO = new WeatherForecastDTO();
        String apiURL = "https://api.openweathermap.org/data/2.5/onecall";
        Map<String, String> params = new HashMap<>();
        params.put("lat", lat);
        params.put("lon", lon);
        params.put("units", "imperial");
        params.put("appid", "b6737b31bfa691de6ae7dcffb8f9a49c");
        JSONObject str = requestsHandler.getRequestJSON(apiURL, params);
        JSONArray str1 = JSONArray.fromObject(str.get("hourly"));

        for (int i = 0; i < 24; i++) {
            weatherForecastDTO.setTemp(Float.parseFloat(str1.getJSONObject(i + 1).getString("temp")), i);
            weatherForecastDTO.setFeel_like(Float.parseFloat(str1.getJSONObject(i + 1).getString("feels_like")), i);
            weatherForecastDTO.setHumidity(str1.getJSONObject(i + 1).getInt("humidity"), i);
            weatherForecastDTO.setPressure(str1.getJSONObject(i + 1).getInt("pressure"), i);
            weatherForecastDTO.setDew_point(Float.parseFloat(str1.getJSONObject(i + 1).getString("dew_point")), i);
            weatherForecastDTO.setClouds(str1.getJSONObject(i + 1).getInt("clouds"), i);
            weatherForecastDTO.setWind_speed(Float.parseFloat(str1.getJSONObject(i + 1).getString("wind_speed")), i);
        }
        return weatherForecastDTO;
    }
}
