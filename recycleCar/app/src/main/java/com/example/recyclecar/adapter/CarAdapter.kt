package com.example.recyclecar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclecar.R
import com.example.recyclecar.data.Car
import com.example.recyclecar.databinding.ItemBinding

class CarAdapter:RecyclerView.Adapter<CarAdapter.CarHolder>() {
    val listCar = mutableListOf<Car>()

    class CarHolder(item: View):ViewHolder(item) {
        val binding = ItemBinding.bind(item)
        fun bind(car: Car) {
            binding.apply {
                logo.setImageResource(car.imageId)
                textLogo.text = car.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CarHolder(view)
    }

    override fun getItemCount() = listCar.size

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        holder.bind(listCar[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCar(car: Car) {
        listCar.add(car)
        notifyDataSetChanged()
    }
}