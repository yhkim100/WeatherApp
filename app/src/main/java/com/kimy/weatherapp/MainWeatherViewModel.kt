package com.kimy.weatherapp

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainWeatherViewModel : ViewModel() {

    private val mutableCurrentWeatherList: MutableLiveData<CurrentWeatherDataModel> = MutableLiveData()
    private val mutableWeatherForecastList: MutableLiveData<ForecastWeatherDataModel> = MutableLiveData()
    //TODO: Move apiKeys to a separate keystore file
    private val apiKey = "1dd64ab3ba579f4f9082c4d09641a761"

    var progressBarIsHidden: MutableLiveData<Int> = MutableLiveData()
    var weatherURL: String = "http://openweathermap.org/img/wn/01d@2x.png"

    fun  getCurrentWeatherData(input: String) : MutableLiveData<CurrentWeatherDataModel>? {
        val call : Call<CurrentWeatherDataModel>
        if(input.toIntOrNull() != null) {
            call = ApiClient.getClient.getCurrentWeatherByZip(zipCode = input+",us", appId = apiKey)
        } else {
            call = ApiClient.getClient.getCurrentWeatherByLocation(location = input, appId = apiKey)
        }
        call.enqueue(object: Callback<CurrentWeatherDataModel> {

            override fun onResponse(call: Call<CurrentWeatherDataModel>?, response: Response<CurrentWeatherDataModel>) {
                var defaultIcon: String = "01d"
                progressBarIsHidden.value = View.INVISIBLE
                if(response.body() != null){
                    defaultIcon = response.body()!!.weather[0].icon
                }
                weatherURL = "http://openweathermap.org/img/wn/${defaultIcon}@2x.png"
                mutableCurrentWeatherList.postValue(response.body())
            }

            override fun onFailure(call: Call<CurrentWeatherDataModel>, t: Throwable) {
                progressBarIsHidden.value = View.INVISIBLE
            }

        })
        return mutableCurrentWeatherList
    }

    fun getForecastWeatherData(input: String) : MutableLiveData<ForecastWeatherDataModel>? {
        val call : Call<ForecastWeatherDataModel>
        if(input.toIntOrNull() != null) {
            call = ApiClient.getClient.getForecastWeatherByZip(zipCode = input+",us", appId = apiKey)
        } else {
            call = ApiClient.getClient.getForecastWeatherByLocation(location = input, appId = apiKey)
        }
        call.enqueue(object: Callback<ForecastWeatherDataModel> {

            override fun onResponse(
                call: Call<ForecastWeatherDataModel>,
                response: Response<ForecastWeatherDataModel>
            ) {
                progressBarIsHidden.value = View.INVISIBLE
                mutableWeatherForecastList.postValue(response.body())
            }

            override fun onFailure(call: Call<ForecastWeatherDataModel>, t: Throwable) {
                progressBarIsHidden.value = View.INVISIBLE
            }

        })
        return mutableWeatherForecastList
    }

}