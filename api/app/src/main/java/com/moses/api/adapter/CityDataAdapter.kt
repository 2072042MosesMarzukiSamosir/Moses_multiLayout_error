package com.moses.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.moses.api.R
import com.moses.api.databinding.CityItemBinding
import com.moses.api.entity.City

class CityDataAdapter(val cities :ArrayList<City> ) : Adapter<CityDataAdapter.CityViewHolder>() {

    private lateinit var cityDataListener: CityDataListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.setCityData(cities[position])
        holder.itemView.setOnClickListener{
            cityDataListener.cityItemClicked(cities[position])
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun setCityDataListener(cityDataListener: CityDataListener){
        this.cityDataListener = cityDataListener
    }

    inner class CityViewHolder(itemView: View) : ViewHolder(itemView) {
        private var binding : CityItemBinding

        init {
            binding = CityItemBinding.bind(itemView)
        }

        fun setCityData(city: City){
            binding.tvCityName.text =  city.name
            binding.tvCityCoordinate.text = city.coordinate.latitude.toString() + ", " + city.coordinate.longtitude.toString()
        }
    }


    interface CityDataListener{
        fun cityItemClicked(city: City)
    }
}