package com.apiCalling

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.FlowsDemo.flowsdemo.R
import com.FlowsDemo.flowsdemo.databinding.WeatherRecItemBinding
import com.bumptech.glide.Glide
import com.example.navigatorapp.Weather.weatherModel.WeatherListw
import com.squareup.picasso.Picasso

class WeatherAdapter(var dataList : ArrayList<WeatherListw>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(val binding : WeatherRecItemBinding) : RecyclerView.ViewHolder(binding.root)  {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            WeatherRecItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = dataList[position]
        holder.binding.txtTime.text = model.dt!!.toString()

        val iconcode=model.weather[0].icon

        Picasso.get().load(iconcode).into(holder.binding.imgWeathericon)
//        Glide.with(holder.itemView)
//            .load(iconcode)
//            .into(holder.binding.imgWeathericon)

        val temp= dataList[position].main?.temp.toString()
        val celsius =(temp.toDouble()-273.0).toInt()
        holder.binding.txtDegree.text= "$celsius°"
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}