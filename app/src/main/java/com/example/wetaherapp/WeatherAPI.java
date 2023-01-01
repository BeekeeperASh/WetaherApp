package com.example.wetaherapp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    String BASE_URL = "https://api.openweathermap.org/";
    String CITY = "Moscow";
    String API_KEY = "dd19bacf7efab59ecb7d1892715b5a3d";
    @GET("data/2.5/forecast")
    Observable<WeatherResponse> getWeather(@Query("q") String city, @Query("appid") String appId);

}
