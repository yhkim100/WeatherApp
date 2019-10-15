package com.kimy.weatherapp

class ForecastWeatherCellData {

    var indexPosition: Int = 0
        get() = field
        set(value) {
            field = value
        }
    var dataModel: ForecastWeatherDataModel? = null
        get() = field
        set(value) {
            field = value
            val tempC = UnitConverter().kelvinToCelsius(value!!.list[indexPosition].main["temp"])
            val tempF = UnitConverter().kelvinToFarenheit(value!!.list[indexPosition].main["temp"])
            temperatureC = "%.2f".format(tempC) + " C"
            temperautreF = "%.2f".format(tempF) + " F"
            timestamp = value!!.list[indexPosition].dt_txt
            description = value!!.list[indexPosition].weather[0].description
            location = value!!.city.name
        }
    var imageURL: String? = null
        get() = field
        set(value) {
            field = value
        }
    var temperatureC: String? = null
    var temperautreF: String? = null
    var timestamp: String? = null
    var description: String? = null
    var location: String? = null

}