package com.ncnendoroid.accessappexercise;

import java.io.Serializable;

public class Table implements Serializable {
    String name,avatar_url,bio,login,location,blog;
    boolean site_admin;

    public Table(String login, String avatar_url, boolean site_admin) {
        this.avatar_url = avatar_url;
        this.login = login;
        this.site_admin = site_admin;
    }

    public Table(String name, String avatar_url, String bio, String login, String location, String blog, boolean site_admin) {
        this.name = name;
        this.avatar_url = avatar_url;
        this.bio = bio;
        this.login = login;
        this.location = location;
        this.blog = blog;
        this.site_admin = site_admin;
    }

    public String getName() {
        return name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getBio() {
        return bio;
    }

    public String getLogin() {
        return login;
    }

    public String getLocation() {
        return location;
    }

    public String getBlog() {
        return blog;
    }

    public boolean isSite_admin() {
        return site_admin;
    }
}
