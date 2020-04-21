package highestaim.com.weather.utils

import highestaim.com.weather.dto.CityDto
import highestaim.com.weather.dto.DayInfoDto
import highestaim.com.weather.enums.Days
import highestaim.com.weather.models.WeatherModel
import highestaim.com.weather.utils.Utils.setWeatherImage

object Converter {

    fun weatherInfoToDayInfoDto(whetherInfo: WeatherModel?, weatherType: String): DayInfoDto {
        whetherInfo?.let {
            val infoDto = DayInfoDto(
                id = it.id,
                cityName = it.name,
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
                    dayName = Days.values()[i].name,
                    cityName = it.name,
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


    fun cityInfoToDayInfoDto(cityDto: CityDto?): DayInfoDto {
        return DayInfoDto(
            cityName = cityDto?.name,
            degree = cityDto?.degree,
            humidity = cityDto?.humidity,
            feelLikes = cityDto?.feelLikes,
            wind = cityDto?.wind,
            type = cityDto?.type,
            image = setWeatherImage(cityDto?.type)
        )
    }
}