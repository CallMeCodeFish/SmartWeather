package com.csc510.smartweather.utilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RequestsHandler {
    public static JSONObject getRequestJSON(String urlStr, Map<String, String> params) {
        HttpURLConnection con = null;
        try {
            URL url = new URL(urlStr + "?" + ParameterStringBuilder.getParamsString(params));
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            int status = con.getResponseCode();
            Reader streamReader = null;
            if (status > 299) {
                streamReader = new InputStreamReader(con.getErrorStream());
            } else {
                streamReader = new InputStreamReader(con.getInputStream());
            }
            return (JSONObject) new JSONParser().parse(streamReader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (con != null)
                con.disconnect();
        }
    }
}
