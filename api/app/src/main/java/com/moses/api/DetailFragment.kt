package com.moses.api

import WeatherApi
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.moses.api.databinding.FragmentDetailBinding
import com.moses.api.entity.City
import com.moses.api.entity.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    private var city: City? = null
    private lateinit var fragmentDetailBinding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return fragmentDetailBinding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(city: City) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, city)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        city?.let { showWeatherData(it) }
    }

    private fun showWeatherData(city: City) {

        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val weatherApi = retrofit.create(WeatherApi::class.java)
        val weatherApiCall =
            weatherApi.getWeatherByCityId(city.id, "fceab2106f05e02c46423d84a41ce169")
        weatherApiCall.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>, response: retrofit2.Response<WeatherResponse>
            ) {
                fragmentDetailBinding.tvDetail1.text = response.body()?.mainWeather?.humidity.toString()
                fragmentDetailBinding.tvDetail2.text = response.body()?.wind?.speed.toString()
                fragmentDetailBinding.tvDetail3.text = response.body()?.mainWeather?.temperature.toString()
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}