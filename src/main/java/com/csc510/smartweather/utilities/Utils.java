package com.csc510.smartweather.utilities;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class Utils {
    public static String[] getLatLongFromJSON(JSONObject json) {
        if (json == null)
            return new String[] {"", ""};
        JSONArray results = JSONArray.fromObject(json.get("results"));
        if (results == null)
            return new String[] {"", ""};
        JSONObject geometry = JSONObject.fromObject(results.getJSONObject(0).get("geometry"));
        JSONObject location = JSONObject.fromObject(geometry.get("location"));
        return new String[] {
                location.getString("lat"),
                location.getString("lng")
        };
    }
    public static String getCityFromJSON(JSONObject json) {
        if (json == null)
            return "";
        JSONArray results = JSONArray.fromObject(json.get("results"));
        if (results == null)
            return "";
        JSONArray address_comps = JSONArray.fromObject(results.getJSONObject(0).get("address_components"));
        for (int i = 0; i < address_comps.size(); i++) {
            JSONObject comp = address_comps.getJSONObject(i);
            JSONArray types = comp.getJSONArray("types");
            for (int j = 0; j < types.size(); j++) {
                if (types.getString(j).contains("locality"))
                    return comp.getString("long_name");
            }
        }
        return "";
    }
}
