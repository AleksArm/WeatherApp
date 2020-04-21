package highestaim.com.weather.utils

import highestaim.com.pixomaticprojectweather.R
import highestaim.com.weather.dto.DayInfoDto
import highestaim.com.weather.enums.Days
import highestaim.com.weather.enums.WeatherTypes.*
import java.util.*

object WeaklyMockData {


    fun getWeaklyMockData(): List<DayInfoDto> {
        return arrayListOf(
            DayInfoDto(
                dayName = Days.MONDAY.name,
                type = CLOUDS.type,
                wind = 2.3f,
                feelLikes = 10,
                humidity = 60,
                degree = 13,
                image = R.mipmap.ic_cloudy_weather
            ),
            DayInfoDto(
                dayName = Days.TUESDAY.name,
                type = RAIN.type,
                wind = 2.5f,
                feelLikes = 11,
                humidity = 70,
                degree = 14,
                image = R.mipmap.ic_shower_rain
            ),
            DayInfoDto(
                dayName = Days.WEDNESDAY.name,
                type = CLEAR.type,
                wind = 2.9f,
                feelLikes = 15,
                humidity = 50,
                degree = 16,
                image = R.mipmap.ic_clear_day
            ),
            DayInfoDto(
                dayName = Days.TUESDAY.name,
                type = EXTREME.type,
                wind = 5f,
                feelLikes = 6,
                humidity = 40,
                degree = 8,
                image = R.mipmap.ic_storm_weather
            ),
            DayInfoDto(
                dayName = Days.FRIDAY.name,
                type = CLOUDS.type,
                wind = 4f,
                feelLikes = 6,
                humidity = 42,
                degree = 9,
                image = R.mipmap.ic_cloudy_weather
            ),
            DayInfoDto(
                dayName = Days.SATURDAY.name,
                type = RAIN.type,
                wind = 3f,
                feelLikes = 4,
                humidity = 30,
                degree = 6,
                image = R.mipmap.ic_shower_rain
            ),
            DayInfoDto(
                dayName = Days.SUNDAY.name,
                type = SNOW.type,
                wind = 1f,
                feelLikes = -2,
                humidity = 25,
                degree = 0,
                image = R.mipmap.ic_snow_weather
            )
        )
    }

}