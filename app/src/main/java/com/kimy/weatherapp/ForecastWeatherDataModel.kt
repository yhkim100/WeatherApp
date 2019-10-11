package com.kimy.weatherapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastWeatherDataModel(

    @Expose
    @SerializedName("cod")
    val cod: String,
    @Expose
    @SerializedName("message")
    val message: Float,
    @Expose
    @SerializedName("cnt")
    val cnt: Int,
    @Expose
    @SerializedName("list")
    val list: Array<DetailedForecastWeatherDataModel>,
    @Expose
    @SerializedName("city")
    val city: DetailedCityDataModel

)

data class DetailedForecastWeatherDataModel (
    @Expose
    @SerializedName("dt")
    val dt: Double,
    @Expose
    @SerializedName("main")
    val main: Map<String, Float>,
    @Expose
    @SerializedName("weather")
    val weather: Array<WeatherDataModel>,
    @Expose
    @SerializedName("clouds")
    val clouds: Map<String, Int>,
    @Expose
    @SerializedName("wind")
    val wind: Map<String, Float>,
    @Expose
    @SerializedName("rain")
    val rain: Map<String, Float>,
    @Expose
    @SerializedName("sys")
    val pod: Map<String, String>,
    @Expose
    @SerializedName("dt_txt")
    val dt_txt: String
)

data class DetailedCityDataModel (
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("coord")
    val coord: Map<String, Float>,
    @Expose
    @SerializedName("country")
    val country: String
)