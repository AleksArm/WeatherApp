package highestaim.com.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import highestaim.com.weather.models.WeatherModel
import highestaim.com.weather.service.WeatherInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository(private val weatherInterface: WeatherInterface) {

    fun getWeather(city: String): LiveData<WeatherModel?> {

        val weatherInfo = MutableLiveData<WeatherModel?>()

        weatherInterface.getWeather(city).enqueue(object : Callback<WeatherModel?> {

            override fun onResponse(call: Call<WeatherModel?>, response: Response<WeatherModel?>) {
                weatherInfo.value = response.body()
            }

            override fun onFailure(call: Call<WeatherModel?>, t: Throwable) {
                println()
            }

        })

        return weatherInfo

    }
}