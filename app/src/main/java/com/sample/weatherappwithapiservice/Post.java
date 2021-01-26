package com.sample.weatherappwithapiservice;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {
    private int id;
    private String name;
    private List<PostWeather> weather;
    private PostWind wind;
    @SerializedName("main")
    private PostMain postMain;

    public PostMain getPostMain(){
        return postMain;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PostWeather> getWeather() {
        return weather;
    }

    public PostWind getWind() {
        return wind;
    }
}
