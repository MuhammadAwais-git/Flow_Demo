package com.example.navigatorapp.Weather.weatherModel


import com.google.gson.annotations.SerializedName

data class Windw(

    @SerializedName("speed" ) var speed : Double? = null,
    @SerializedName("deg"   ) var deg   : Int?    = null,
    @SerializedName("gust"  ) var gust  : Double? = null
)