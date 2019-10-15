package com.kimy.weatherapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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
import kotlinx.android.synthetic.main.activity_home_weather.*


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

        //Set up Observers and load default values
        mainWeatherViewModel.getForecastWeatherData("27617")?.observe(this, Observer {
            if(it != null) {
                //Update values in adapter which will trigger UI updates
                forecastAdapter.setForecastWeatherDataModel(it)
                binding.executePendingBindings()
            }
        })

        mainWeatherViewModel.getCurrentWeatherData("27617")?.observe(this, Observer {
            if(it!= null) {
                //Update UI and Notify others of new CurrentWeatherModel
                binding.locationText.setText("${it.location}")
                binding.weatherDescriptionTxt.setText("${it.weather[0].main}, ${it.weather[0].description}")

                val sdf = java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                val date = java.util.Date(it.dt * 1000)
                val format = sdf.format(date)
                binding.timestampUpdateText.setText(format)

                //TODO: Switch between
                val tempF = UnitConverter().kelvinToFarenheit(it.main["temp"])
                currentTempText.setText("%.2f".format(tempF) + " F")
            }
        })

        getForecastBtn.setOnClickListener {
            mainWeatherViewModel.getCurrentWeatherData(input = inputLocationTxtBox.text.toString())
            mainWeatherViewModel.getForecastWeatherData(input = inputLocationTxtBox.text.toString())
        }


        //Dismiss Keyboard listeners
        currentWeatherCardView.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                hideKeyboard(p0)
                return false
            }
        })
        inputLocationTxtBox.setOnFocusChangeListener(object: View.OnFocusChangeListener{
            override fun onFocusChange(p0: View?, p1: Boolean) {
                if(!p1) hideKeyboard(view = p0)
            }
        })
        recyclerView.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                hideKeyboard(p0)
                return false
            }
        })
    }

    fun hideKeyboard(view: View?) {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }


}

