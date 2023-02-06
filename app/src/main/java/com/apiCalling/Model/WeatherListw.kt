package com.example.navigatorapp.Weather.weatherModel


import com.google.gson.annotations.SerializedName

data class WeatherListw(
    @SerializedName("dt"         ) var dt         : Int?               = null,
    @SerializedName("main"       ) var main       : Mainw?              = Mainw(),
    @SerializedName("weather"    ) var weather    : ArrayList<Weatherw> = ArrayList(),
    @SerializedName("clouds"     ) var clouds     : CloudsW?            = CloudsW(),
    @SerializedName("wind"       ) var wind       : Windw?              = Windw(),
    @SerializedName("visibility" ) var visibility : Int?               = null,
    @SerializedName("pop"        ) var pop        : Double?               = null,
    @SerializedName("sys"        ) var sys        : Sysw?               = Sysw(),
    @SerializedName("dt_txt"     ) var dtTxt      : String?            = null
)