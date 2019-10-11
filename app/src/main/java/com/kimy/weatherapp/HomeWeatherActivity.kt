package com.kimy.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimy.weatherapp.databinding.ActivityHomeWeatherBinding


class HomeWeatherActivity : AppCompatActivity() {

    private lateinit var mainWeatherViewModel: MainWeatherViewModel
    private lateinit var binding: ActivityHomeWeatherBinding

    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var forecastAdapter:ForecastWeatherDataAdapter
    lateinit var weatherIconImageView: ImageView
    lateinit var getForecastBtn: Button
    lateinit var inputLocationTxtBox: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get and bind ViewModel to XML
        mainWeatherViewModel = ViewModelProviders.of(this).get(MainWeatherViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_weather)
        binding.setLifecycleOwner(this)
        binding.setMainWeatherViewModel(mainWeatherViewModel)

        progressBar = binding.progressbar
        weatherIconImageView = binding.weatherImageView
        getForecastBtn = binding.fetchWeatherBtn
        inputLocationTxtBox = binding.inputLocationTextView

        //bind RecyclerView
        recyclerView = binding.recyclerView
        forecastAdapter = ForecastWeatherDataAdapter(this)
        recyclerView.adapter = forecastAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //Set up Observers
        mainWeatherViewModel.getForecastWeatherDataByZip()?.observe(this, Observer {
            if(it != null) {
                //Update values in adapter which will trigger UI updates
                forecastAdapter.setForecastWeatherDataModel(it)
                binding.executePendingBindings()
            }
        })

        mainWeatherViewModel.getCurrentWeatherData()?.observe(this, Observer {
            if(it!= null) {
                //Notify others of new CurrentWeatherModel
            }
        })

        getForecastBtn.setOnClickListener {
            mainWeatherViewModel.getCurrentWeatherData()
        }

    }


}

