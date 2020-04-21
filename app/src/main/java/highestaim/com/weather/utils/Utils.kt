package highestaim.com.weather.utils

import highestaim.com.pixomaticprojectweather.R
import highestaim.com.weather.enums.WeatherTypes
import java.util.*

object Utils {
    fun getCurrentDay(): Int {
        val calendar: Calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    fun setWeatherImage(weatherInfo: String?): Int {
        return when (weatherInfo) {
            WeatherTypes.RAIN.type -> R.mipmap.ic_shower_rain
            WeatherTypes.SNOW.type -> R.mipmap.ic_snow_weather
            WeatherTypes.EXTREME.type -> R.mipmap.ic_storm_weather
            WeatherTypes.CLEAR.type -> R.mipmap.ic_clear_day
            WeatherTypes.CLOUDS.type -> R.mipmap.ic_cloudy_weather
            WeatherTypes.DRIZZLE.type -> R.mipmap.ic_shower_rain
            else -> R.mipmap.ic_unknown
        }
    }
}
