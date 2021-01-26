package com.sample.weatherappwithapiservice;

import com.google.gson.annotations.SerializedName;

public class PostWeather {
    private int id;
    private String main;
    @SerializedName("description")
    private String des;
    private String icon;

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDes() {
        return des;
    }

    public String getIcon() {
        return icon;
    }



}
