package com.ncnendoroid.accessappexercise;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Json_Detail {
    public static List<Table> getTable(String response){
        String name,image,bio,login,location,blog;
        Boolean site_admin;
        List<Table> tableList = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(response);
            name = object.optString("name");
            image = object.optString("avatar_url");
            bio = object.optString("bio");
            login = object.optString("login");
            location = object.optString("location");
            blog = object.optString("blog");
            site_admin = object.optBoolean("site_admin");
            if (location == null)location = "";
            if (blog == null)blog = "";
            if (bio == null)bio = "";

            tableList.add(new Table(name, image, bio,login,location,blog,site_admin));

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return tableList;
    }
}
