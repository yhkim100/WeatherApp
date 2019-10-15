package com.kimy.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Detailed Example: https://square.github.io/retrofit/
interface ApiInterface {
    @GET("weather")
    fun getCurrentWeatherByZip(@Query("zip") zipCode: String, @Query("APPID") appId: String): Call<CurrentWeatherDataModel>
    @GET("weather")
    fun getCurrentWeatherByLocation(@Query("q") location: String, @Query("APPID") appId: String): Call<CurrentWeatherDataModel>

    @GET("forecast")
    fun getForecastWeatherByZip(@Query("zip") zipCode: String, @Query("APPID") appId: String): Call<ForecastWeatherDataModel>
    @GET("forecast")
    fun getForecastWeatherByLocation(@Query("q") location: String, @Query("APPID") appId: String): Call<ForecastWeatherDataModel>
}