package com.kimy.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kimy.weatherapp.databinding.ForecastWeatherListItemBinding

class ForecastWeatherDataAdapter( private val context: Context) : RecyclerView.Adapter<ForecastWeatherDataAdapter.ForecastWeatherViewHolder>() {

    private var mForecastWeatherDataModelList = ArrayList<ForecastWeatherDataModel>()

    fun setForecastWeatherDataModel(dataModel: ForecastWeatherDataModel) {
        mForecastWeatherDataModelList.clear()
        mForecastWeatherDataModelList.add(dataModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastWeatherViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val forecastWeatherListItemBinding = ForecastWeatherListItemBinding.inflate(layoutInflater, parent, false)
        return ForecastWeatherViewHolder(forecastWeatherListItemBinding)
    }

    override fun onBindViewHolder(holder: ForecastWeatherViewHolder, position: Int) {
        val weatherInfo = mForecastWeatherDataModelList.first().list[position]
        holder.bind(weatherInfo)
    }

    override fun getItemCount(): Int {
        if (mForecastWeatherDataModelList.isNotEmpty()) {
            return mForecastWeatherDataModelList.first().list.count()
        } else {
            return 0
        }
    }

    class ForecastWeatherViewHolder(var forecastWeatherListItemBinding: ForecastWeatherListItemBinding) : RecyclerView.ViewHolder(forecastWeatherListItemBinding.root) {

        fun bind(weatherInfo :DetailedForecastWeatherDataModel) {
            forecastWeatherListItemBinding.detailedForecastWeatherDataModel = weatherInfo
        }
    }
}