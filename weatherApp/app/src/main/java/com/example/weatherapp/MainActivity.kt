package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.fragments.MainFragment


const val API_KEY_1 = "fe37edf154d7cc4d2716612b2d91f49f" // openWeatherMap
const val API_KEY_2 = "972d71ee52374f3ea7c144402232202" // https://www.weatherapi.com/my/
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.place_holder, MainFragment.newInstance()).commit()
    }
}