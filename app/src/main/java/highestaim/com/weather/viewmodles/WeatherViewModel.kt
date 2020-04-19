package highestaim.com.weather.viewmodles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import highestaim.com.weather.models.WeatherModel
import highestaim.com.weather.repository.WeatherRepository

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getWeather(city: String): LiveData<WeatherModel?> {
        return weatherRepository.getWeather(city)
    }
}