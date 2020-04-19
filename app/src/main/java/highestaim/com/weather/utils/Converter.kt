package highestaim.com.weather.utils

import highestaim.com.pixomaticprojectweather.R
import highestaim.com.weather.dto.DayInfoDto
import highestaim.com.weather.enums.Days
import highestaim.com.weather.enums.WeatherTypes.*
import highestaim.com.weather.models.WeatherModel

object Converter {

    fun weatherInfoToDayInfoDto(whetherInfo: WeatherModel?, weatherType: String): DayInfoDto {
        whetherInfo?.let {
            val infoDto = DayInfoDto(
                id = it.id,
                name = "",
                degree = it.main?.temp?.toInt() ?: 0,
                humidity = it.main?.humidity?.toInt() ?: 0,
                feelLikes = it.main?.feelsLike?.toInt() ?: 0,
                wind = it.wind?.speed?.toFloat() ?: 0.0f,
                type = it.weather?.get(0)?.main ?: ""
            )

            infoDto.image = setWeatherImage(weatherType)
            return infoDto
        }
        return DayInfoDto()
    }

    fun weatherInfoToListDayInfoDto(whetherInfo: WeatherModel?): List<DayInfoDto> {
        val info = arrayListOf<DayInfoDto>()
        whetherInfo?.let {
            for (i in 0..6) {
                val dayInfoDto = DayInfoDto(
                    id = it.id,
                    name = Days.values()[i].name,
                    degree = it.main?.temp?.toInt() ?: 0,
                    humidity = it.main?.humidity?.toInt() ?: 0,
                    feelLikes = it.main?.feelsLike?.toInt() ?: 0,
                    wind = it.wind?.speed?.toFloat() ?: 0.0f,
                    type = it.weather?.get(0)?.main ?: ""
                )
                dayInfoDto.image = setWeatherImage(it.weather?.get(0)?.main)
                info.add(dayInfoDto)
            }
        }
        return info
    }

    private fun setWeatherImage(weatherInfo: String?): Int {
        return when (weatherInfo) {
            RAIN.type -> R.mipmap.ic_shower_rain
            SNOW.type -> R.mipmap.ic_snow_weather
            EXTREME.type -> R.mipmap.ic_storm_weather
            CLEAR.type -> R.mipmap.ic_clear_day
            CLOUDS.type -> R.mipmap.ic_cloudy_weather
            else -> 0
        }
    }
}