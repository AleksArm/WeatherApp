package highestaim.com.weather.service

import highestaim.com.weather.models.WeatherModel
import highestaim.com.weather.remote.RetroClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInterface {

    /***
     * For temperature in Fahrenheit use units=imperial
     * For temperature in Celsius use units=metric
     * Temperature in Kelvin is used by default, no need to use units parameter in API call
     */
    @GET("weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("appid") appId: String = "your_kee",
        @Query("units") imperial: String = "metric"
    ): Call<WeatherModel?>


    companion object {
        fun create(): WeatherInterface {
            return RetroClass.retroInstance.create(WeatherInterface::class.java)
        }
    }


}
