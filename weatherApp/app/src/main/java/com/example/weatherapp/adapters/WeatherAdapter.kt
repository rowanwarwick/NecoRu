package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherapp.R
import com.example.weatherapp.data.DayItem
import com.example.weatherapp.databinding.ItemRecyclerBinding
import com.squareup.picasso.Picasso

class WeatherAdapter(val listener: Listener?): ListAdapter<DayItem, WeatherAdapter.OurHolder>(Comparator()) {

    class OurHolder(view: View, val listener: Listener?):ViewHolder(view) {

        val binding = ItemRecyclerBinding.bind(view)

        fun bind(day: DayItem) {
            binding.apply {
                itemDate.text = day.time
                itemCondition.text = day.conditionWeather
                val temp = if (day.currectTemp.isNotEmpty() && day.currectTemp.first() != 'a') day.currectTemp + "°C" else "${day.minTemp}°C/${day.maxTemp}°C"
                itemTemp.text = temp
                Picasso.get().load("https:" + day.imageURLWeather).into(itemImage)
            }
            itemView.setOnClickListener {
                listener?.onClick(day)
            }
        }
    }

    class Comparator: DiffUtil.ItemCallback<DayItem>() {
        override fun areItemsTheSame(oldItem: DayItem, newItem: DayItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DayItem, newItem: DayItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OurHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return OurHolder(view, listener)
    }

    override fun onBindViewHolder(holder: OurHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface Listener {
        fun onClick(item: DayItem)
    }
}