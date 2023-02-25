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
import com.example.weatherapp.databinding.FragmentDaysBinding
import com.example.weatherapp.view_model.MainViewModel

class DaysFragment : Fragment(), WeatherAdapter.Listener {

    lateinit var binding: FragmentDaysBinding
    lateinit var adapter: WeatherAdapter
    val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        model.liveDataList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    fun initRecycleView() {
        binding.apply {
            list.layoutManager = LinearLayoutManager(requireContext())
            adapter = WeatherAdapter(this@DaysFragment)
            list.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    override fun onClick(item: DayItem) {
        model.liveDataCurrent.value = item
    }
}