package com.example.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapters.WeatherAdapter
import com.example.weatherapp.data.DayItem
import com.example.weatherapp.databinding.FragmentHourBinding
import com.example.weatherapp.view_model.MainViewModel
import org.json.JSONArray
import org.json.JSONObject

class HourFragment : Fragment() {

    lateinit var binding: FragmentHourBinding
    lateinit var adapter: WeatherAdapter
    val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        model.liveDataCurrent.observe(viewLifecycleOwner){
            adapter.submitList(getHoursList(it))
        }
    }

    fun initRecycleView(){
        binding.apply {
            list.layoutManager = LinearLayoutManager(this@HourFragment.context)
            adapter = WeatherAdapter(null)
            list.adapter = adapter
        }
    }

    fun getHoursList(item: DayItem): List<DayItem>{
        val hours = JSONArray(item.hours)
        val list = mutableListOf<DayItem>()
        for (i in 0 until hours.length()) {
            val hour = DayItem(
                "",
                (hours[i] as JSONObject).getString("time"),
                (hours[i] as JSONObject).getJSONObject("condition").getString("text"),
                (hours[i] as JSONObject).getJSONObject("condition").getString("icon"),
                (hours[i] as JSONObject).getString("temp_c"),
                "",
                "",
                ""
            )
            list.add(hour)
        }
        return list
    }

    companion object {
        @JvmStatic
        fun newInstance() = HourFragment()
    }
}