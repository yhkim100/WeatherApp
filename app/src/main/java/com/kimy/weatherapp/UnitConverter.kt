package com.kimy.weatherapp

class UnitConverter {

    fun kelvinToFarenheit(kelvin: Float?): Double{
        if(kelvin != null) {
            return kelvin - 273.15 * (9/5) + 32
        } else {
            return 0.0
        }
    }

    fun kelvinToCelsius(kelvin: Float?): Double{
        if(kelvin != null){
            return kelvin - 273.15
        } else {
            return 0.0
        }
    }
}