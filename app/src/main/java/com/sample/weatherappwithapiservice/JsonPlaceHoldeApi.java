package com.sample.weatherappwithapiservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHoldeApi {

    @GET("data/2.5/weather")
    Call<Post> getPost(@Query("q") String weather, @Query("appid") String apiKey);
}
