package com.csc510.smartweather.weatherInfo;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;


@Controller
public class QueryWeather {
    @Autowired
    RestTemplate restTemplate;

    public CurrentWeather cw = new CurrentWeather();

    public WeatherForecast wf = new WeatherForecast();

    public CurrentWeather CurrentWeatherInfo(String lat,String lon){
        String apiURL = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&units=imperial&appid=b6737b31bfa691de6ae7dcffb8f9a49c";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL,String.class);
        //get all
        String jsonsting = responseEntity.getBody();
        JSONObject str = JSONObject.fromObject(jsonsting);
        JSONObject str1 = JSONObject.fromObject(str.get("main"));
        JSONArray str2 = JSONArray.fromObject(str.get("weather"));
        JSONObject str3 = JSONObject.fromObject(str.get("wind"));
        JSONObject str4 = JSONObject.fromObject(str.get("clouds"));

        cw.setFeel_like(Float.parseFloat(str1.getString("feels_like")));
        cw.setHumidity(str1.getInt("humidity"));
        cw.setPressure(str1.getInt("pressure"));
        cw.setTemp(Float.parseFloat(str1.getString("temp")));
        cw.setTemp_min(Float.parseFloat(str1.getString("temp_min")));
        cw.setTemp_max(Float.parseFloat(str1.getString("temp_max")));
        cw.setId(str2.getJSONObject(0).getInt("id"));
        cw.setMainInfo(str2.getJSONObject(0).getString("main"));
        cw.setIcon(str2.getJSONObject(0).getString("icon"));
        cw.setWind_speed(Float.parseFloat(str3.getString("speed")));
        cw.setWind_deg(str3.getInt("deg"));
        cw.setClouds(str4.getInt("all"));
        cw.setCity(str.getString("name"));

//        System.out.println(cw.getIcon());
//        System.out.println(str);
        return cw;
    }

    public WeatherForecast WeatherForecastInfo(String lat,String lon){
        String apiURL = "https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&units=imperial&appid=b6737b31bfa691de6ae7dcffb8f9a49c";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL,String.class);
        //get all
        String jsonsting = responseEntity.getBody();
        JSONObject str = JSONObject.fromObject(jsonsting);
        JSONArray str1 = JSONArray.fromObject(str.get("hourly"));

        for (int i = 0; i < 24; i++) {
            wf.setiTemp(Float.parseFloat(str1.getJSONObject(i+1).getString("temp")), i);
            wf.setiFeel_like(Float.parseFloat(str1.getJSONObject(i+1).getString("feels_like")), i);
            wf.setiHumidity(str1.getJSONObject(i+1).getInt("humidity"), i);
            wf.setiPressure(str1.getJSONObject(i+1).getInt("pressure"), i);
            wf.setiDew_point(Float.parseFloat(str1.getJSONObject(i+1).getString("dew_point")), i);
            wf.setiClouds(str1.getJSONObject(i+1).getInt("clouds"), i);
            wf.setiWind_speed(Float.parseFloat(str1.getJSONObject(i+1).getString("wind_speed")), i);
        }

//        System.out.println(wf);
//        System.out.println(str);
        return wf;
    }
}
