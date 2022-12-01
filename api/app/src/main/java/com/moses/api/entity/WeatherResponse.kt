package com.moses.api.entity

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
    @SerializedName("weather")val weather: ArrayList<Weather>,
    @SerializedName("main")val mainWeather: MainWeather,
    @SerializedName("wind")val wind: Wind
    )