package com.csc510.smartweather.utilities;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Component
public class RequestsHandler {
    @Autowired
    RestTemplate restTemplate;

    public JSONObject getRequestJSON(String urlStr, Map<String, String> params) {
        String url = urlStr + "?" + ParameterStringBuilder.getParamsString(params);
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.getForEntity(new URL(url).toString(), String.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        String jsonString = responseEntity.getBody();
        return JSONObject.fromObject(jsonString);
    }
}
