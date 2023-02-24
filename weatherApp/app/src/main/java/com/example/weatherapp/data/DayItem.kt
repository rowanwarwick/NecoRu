package com.example.weatherapp.data

data class DayItem(
    val city: String,
    val time: String,
    val conditionWeather: String,
    val imageURLWeather: String,
    val currectTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val hours: String
)
