package com.ncnendoroid.accessappexercise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Json {
    public static List<Table> getTable(String response){
        String name,avatar_url;
        Boolean site_admin;
        List<Table> tableList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(response);
            for (int i = 0; i < 20; i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.optString("login");
                avatar_url = object.optString("avatar_url");
                site_admin = object.optBoolean("site_admin");
                tableList.add(new Table(name,avatar_url,site_admin));
            }
            } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return tableList;
    }
}
