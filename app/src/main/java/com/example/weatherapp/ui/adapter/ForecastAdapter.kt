package com.example.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemForecastBinding
import java.util.Locale

//
data class ForecastItem(val day: String, val temp: String, val condition: String)

class ForecastAdapter(private var forecastList: List<ForecastItem>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    // View Binding
    inner class ForecastViewHolder(val binding: ItemForecastBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemForecastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = forecastList[position]

        // XML
        holder.binding.tvForecastDay.text = item.day
        holder.binding.tvForecastTemp.text = item.temp

        //
        when (item.condition.lowercase(Locale.ROOT)) {
            "clear" -> holder.binding.ivForecastIcon.setImageResource(R.drawable.ic_sunny)
            "clouds" -> holder.binding.ivForecastIcon.setImageResource(R.drawable.ic_cloudy)
            "rain" -> holder.binding.ivForecastIcon.setImageResource(R.drawable.ic_rainy)
            else -> holder.binding.ivForecastIcon.setImageResource(R.drawable.ic_sunny)
        }
    }

    override fun getItemCount(): Int = forecastList.size

    //
    fun updateData(newList: List<ForecastItem>) {
        this.forecastList = newList
        notifyDataSetChanged()
    }
}