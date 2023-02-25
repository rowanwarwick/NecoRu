package com.example.weatherapp.fragments

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.API_KEY_2
import com.example.weatherapp.adapters.ViewPagerAdapter
import com.example.weatherapp.data.DayItem
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.dialog.DialogGPS
import com.example.weatherapp.dialog.DialogSearchCity
import com.example.weatherapp.view_model.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.net.URLDecoder
import java.net.URLEncoder

class MainFragment : Fragment() {
    lateinit var locationClient: FusedLocationProviderClient
    lateinit var permissionLauncher: ActivityResultLauncher<String>
    lateinit var binding: FragmentMainBinding
    val listTabNames = listOf("hours", "days")
    val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun updateCurrentDay() {
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            binding.apply {
                val minMax = "${it.minTemp}°C/${it.maxTemp}°C"
                val temp = if (it.currectTemp.isNotEmpty()) it.currectTemp.replace(
                    "a",
                    ""
                ) + "°C" else minMax
                time.text = it.time
                city.text = it.city
                currentTemp.text = temp
                condition.text = it.conditionWeather
                maxMinTemp.text = if (it.currectTemp.isEmpty()) "" else minMax
                Picasso.get().load("https:" + it.imageURLWeather).into(imageWeather)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        updateCurrentDay()
        init()
        checkLocation()
    }


    fun init() {
        locationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        val adapter = ViewPagerAdapter(activity as FragmentActivity)
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = listTabNames[position]
        }.attach()
        binding.sync.setOnClickListener {
            binding.tabLayout.selectTab((binding.tabLayout.getTabAt(0)))
            checkLocation()
        }
        binding.search.setOnClickListener {
            DialogSearchCity.searchByCity(requireContext(), object : DialogSearchCity.ListenerCity{
                override fun onClick(name: String) {
                    requestWeather(name)
                }
            })
        }
    }

    fun checkLocation() {
        if (isLocationEnable() == true) getLocation()
        else DialogGPS.locationSettingsDialog(requireContext(), object : DialogGPS.Listener{
            override fun onClick() {
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
        })
    }

    fun isLocationEnable(): Boolean {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun getLocation() {
        val token = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, token.token)
            .addOnCompleteListener {
                requestWeather("${it.result.latitude},${it.result.longitude}")
            }
    }

    fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    fun permissionListener() {
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()){
                checkLocation()
            }
    }

    fun requestWeather(city: String) {
        val url =
            "http://api.weatherapi.com/v1/forecast.json?key=${API_KEY_2}&q=${city}&days=5&aqi=no&alerts=no&lang=ru"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { result ->
                parseDataForDayItem(URLDecoder.decode(URLEncoder.encode(result, "iso8859-1"),"UTF-8"))
            },
            { error ->
                Log.i("MyLog", error.toString())
            }
        )
        queue.add(request)
    }

    fun parseDataForDayItem(result: String) {
        val data = JSONObject(result)
        parseAllDays(data)
        parseCurrentDay(data)
    }

    fun parseCurrentDay(data: JSONObject) {
        val day = DayItem(
            data.getJSONObject("location").getString("name"),
            data.getJSONObject("current").getString("last_updated"),
            data.getJSONObject("current").getJSONObject("condition").getString("text"),
            data.getJSONObject("current").getJSONObject("condition").getString("icon"),
            data.getJSONObject("current").getString("temp_c"),
            (data.getJSONObject("forecast")
                .getJSONArray("forecastday")[0] as JSONObject).getJSONObject("day")
                .getString("maxtemp_c"),
            (data.getJSONObject("forecast")
                .getJSONArray("forecastday")[0] as JSONObject).getJSONObject("day")
                .getString("mintemp_c"),
            (data.getJSONObject("forecast")
                .getJSONArray("forecastday")[0] as JSONObject).getJSONArray("hour").toString()
        )
        model.liveDataCurrent.value = day
    }

    fun parseAllDays(data: JSONObject): List<DayItem> {
        val list = mutableListOf<DayItem>()
        val daysArray = data.getJSONObject("forecast").getJSONArray("forecastday")
        val name = data.getJSONObject("location").getString("name")
        for (index in 0 until daysArray.length()) {
            val day = daysArray[index] as JSONObject
            val item = DayItem(
                name,
                day.getString("date"),
                day.getJSONObject("day").getJSONObject("condition").getString("text"),
                day.getJSONObject("day").getJSONObject("condition").getString("icon"),
                if (index != 0) "" else "a" + data.getJSONObject("current").getString("temp_c"),
                day.getJSONObject("day").getString("maxtemp_c"),
                day.getJSONObject("day").getString("mintemp_c"),
                day.getJSONArray("hour").toString()
            )
            list.add(item)
        }
        model.liveDataList.value = list
        return list
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}