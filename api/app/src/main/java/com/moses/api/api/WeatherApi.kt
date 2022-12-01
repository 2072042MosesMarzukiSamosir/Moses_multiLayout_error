import com.moses.api.entity.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

 // https://api.openweathermap.org/data/2.5/weather?id={city id}&appid={API key}
 @GET("weather")
 fun getWeatherByCityId(@Query("id") cityId: Int, @Query("appid") apiKey: String): Call<WeatherResponse>

 // https://api.openweathermap.org/data/2.5/weather?zip={zip code},{country code}&appid={API key}
 @GET("weather")
 fun getWeatherByZipCode(@Query("zip") zipCode: String, @Query("appid")apiKey: String)
}