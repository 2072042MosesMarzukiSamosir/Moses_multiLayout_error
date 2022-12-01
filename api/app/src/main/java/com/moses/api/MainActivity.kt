package com.moses.api

import WeatherApi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moses.api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentTransaction = supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id,MainFragment.newInstance())
        fragmentTransaction.commit()
    }
}


//        val requestQueue = Volley.newRequestQueue(this@MainActivity)
//        val uri = Uri.parse("https://api.openweathermap.org/data/2.5/weather").buildUpon()
//            .appendQueryParameter("id", city.id.toString())
//            .appendQueryParameter("appid", "fceab2106f05e02c46423d84a41ce169")
//            .build()
//        val request = StringRequest(Request.Method.GET, uri.toString(),
//            Response.Listener {
//                val gson = Gson()
//                val weatherResponse = gson.fromJson<WeatherResponse>(it, WeatherResponse::class.java)
//                Toast.makeText(this@MainActivity, weatherResponse.mainWeather.toString(), Toast.LENGTH_SHORT).show()
//            },
//            Response.ErrorListener {
//                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
//            })
//        requestQueue.add(request)
//    }
//}