package com.moses.api

import WeatherApi
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.moses.api.adapter.CityDataAdapter
import com.moses.api.databinding.FragmentMainBinding
import com.moses.api.entity.City
import com.moses.api.entity.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    private lateinit var cities: ArrayList<City>
    private lateinit var cityDataAdapter: CityDataAdapter
    private lateinit var fragmentMainBinding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cities = ArrayList()
        cityDataAdapter = CityDataAdapter(cities)
        cityDataAdapter.setCityDataListener(object : CityDataAdapter.CityDataListener {
            override fun cityItemClicked(city: City) {
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
//                if (fragmentMainBinding.fragmentContainerTablet != null){
//                    fragmentTransaction?.replace(R.id.fragment_container_tablet, DetailFragment.newInstance(city))
//                }else{
                    fragmentTransaction?.replace(R.id.fragment_container, DetailFragment.newInstance(city))
                    fragmentTransaction?.addToBackStack(null)
//                }
                fragmentTransaction?.commit()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        fragmentMainBinding.rvCities.layoutManager = LinearLayoutManager(activity)
        fragmentMainBinding.rvCities.adapter = cityDataAdapter
        fragmentMainBinding.srLayout.setOnRefreshListener {
            this.fetchCitydataFromFile()
            fragmentMainBinding.srLayout.isRefreshing = false

        }
        return fragmentMainBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        this.fetchCitydataFromFile()
    }

    private fun fetchCitydataFromFile() {
        val inputStream = activity?.assets?.open("city.list.json")
        val reader = JsonReader(InputStreamReader(inputStream, Charsets.UTF_8))
        val gson = Gson()
        val cities = gson.fromJson<Array<City>>(reader, Array<City>::class.java)
        this.cities.clear()
        this.cities.addAll(cities)
        cityDataAdapter.notifyItemChanged(0)
    }
}