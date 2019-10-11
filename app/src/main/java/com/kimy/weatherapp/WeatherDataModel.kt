package com.kimy.weatherapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherDataModel(

    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("main")
    val main: String,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("icon")
    val icon: String

)