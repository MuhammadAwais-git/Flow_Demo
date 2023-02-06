package com.example.navigatorapp.Weather.weatherModel


import com.google.gson.annotations.SerializedName

data class DataWeatherModelw(
    @SerializedName("cod"     ) var cod     : String?         = null,
    @SerializedName("message" ) var message : Int?            = null,
    @SerializedName("cnt"     ) var cnt     : Int?            = null,
    @SerializedName("list"    ) var list    : ArrayList<WeatherListw> = ArrayList(),
    @SerializedName("city"    ) var city    : CityW?           = CityW()

)