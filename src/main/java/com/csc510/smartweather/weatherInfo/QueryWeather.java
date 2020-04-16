package com.csc510.smartweather.weatherInfo;

import net.sf.json.JSONObject;
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


    public CurrentWeather CurrentWeatherInfo(){
        String apiURL = "http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6737b31bfa691de6ae7dcffb8f9a49c";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL,String.class);
        //get all
        String jsonsting = responseEntity.getBody();
        JSONObject str = JSONObject.fromObject(jsonsting);
        JSONObject str1 = JSONObject.fromObject(str.get("main"));

        cw.setFeel_like(str1.getInt("feels_like"));
        cw.setHumidity(str1.getInt("humidity"));
        cw.setPressure(str1.getInt("pressure"));
        cw.setTemp(str1.getInt("temp"));
        cw.setTemp_min(str1.getInt("temp_min"));
        cw.setTemp_max(str1.getInt("temp_max"));
        System.out.println(cw);

        return cw;
    }
}
