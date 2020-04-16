package com.csc510.smartweather.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Utils {
    public static float[] getLatLongFromJSON(JSONObject json) {
        JSONArray results = (JSONArray) json.get("results");
        if (results == null)
            return new float[] {0, 0};
        JSONObject geometry = (JSONObject) ((JSONObject) results.get(0)).get("geometry");
        JSONObject location = (JSONObject) geometry.get("location");
        return new float[] {
                Float.parseFloat(location.get("lat").toString()),
                Float.parseFloat(location.get("lng").toString())
        };
    }
}
