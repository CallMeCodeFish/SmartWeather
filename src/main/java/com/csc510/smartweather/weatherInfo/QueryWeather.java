package com.csc510.smartweather.weatherInfo;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;


@Controller
public class QueryWeather {
    @Autowired
    RestTemplate restTemplate;

    public CurrentWeather cw = new CurrentWeather();

    public WeatherForecast wf = new WeatherForecast();


    public CurrentWeather CurrentWeatherInfo(){
        String apiURL = "http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6737b31bfa691de6ae7dcffb8f9a49c";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL,String.class);
        //get all
        String jsonsting = responseEntity.getBody();
        JSONObject str = JSONObject.fromObject(jsonsting);
        JSONObject str1 = JSONObject.fromObject(str.get("main"));


        cw.setFeel_like(Float.parseFloat(str1.getString("feels_like")));
        cw.setHumidity(str1.getInt("humidity"));
        cw.setPressure(str1.getInt("pressure"));
        cw.setTemp(Float.parseFloat(str1.getString("temp")));
        cw.setTemp_min(Float.parseFloat(str1.getString("temp_min")));
        cw.setTemp_max(Float.parseFloat(str1.getString("temp_max")));
        System.out.println(cw);

        return cw;
    }

    public WeatherForecast WeatherForecastInfo(){
        String apiURL = "https://api.openweathermap.org/data/2.5/onecall?lat=60.99&lon=30.9&appid=b6737b31bfa691de6ae7dcffb8f9a49c";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL,String.class);
        //get all
        String jsonsting = responseEntity.getBody();
        JSONObject str = JSONObject.fromObject(jsonsting);
        JSONArray str1 = JSONArray.fromObject(str.get("hourly"));

        for (int i = 0; i < 24; i++) {
            wf.setiFeel_like(Float.parseFloat(str1.getJSONObject(i).getString("feels_like")), i);
            wf.setiHumidity(str1.getJSONObject(i).getInt("humidity"), i);
            wf.setiPressure(str1.getJSONObject(i).getInt("pressure"), i);
            wf.setiTemp(Float.parseFloat(str1.getJSONObject(i).getString("temp")), i);
        }

        System.out.println(wf);
        return wf;

//        JSONObject str1 = JSONObject.fromObject(str.get("current"));
//        cw.setFeel_like(str1.getInt("feels_like"));
//        cw.setHumidity(str1.getInt("humidity"));
//        cw.setPressure(str1.getInt("pressure"));
//        cw.setTemp(str1.getInt("temp"));
//        return cw;

    }
}