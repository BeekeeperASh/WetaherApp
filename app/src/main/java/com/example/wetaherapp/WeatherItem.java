package com.example.wetaherapp;

import androidx.arch.core.internal.SafeIterableMap;

import java.util.List;

public class WeatherItem {
    public String dt_txt;
    public MainInfo main;
    public List<WeatherInfo> weather;

    class MainInfo{
        public Double temp;
        public Double humidity;
    }
    class WeatherInfo {
        public String main;
    }
}
