package com.kimy.weatherapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentWeatherDataModel(

    @Expose
    @SerializedName("coord")
    val coord: Map<String, Float>,
    @Expose
    @SerializedName("weather")
    val weather: Array<WeatherDataModel>,
    @Expose
    @SerializedName("base")
    val base: String,
    @Expose
    @SerializedName("main")
    val main: Map<String, Float>,
    @Expose
    @SerializedName("visibility")
    val visibility: Int,
    @Expose
    @SerializedName("wind")
    val wind: Map<String, Float>


)

