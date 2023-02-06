package com.apiCalling


import com.example.navigatorapp.Weather.weatherModel.DataWeatherModelw

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfacepost {
    @GET("forecast?")
    suspend fun getWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
    ): DataWeatherModelw
}