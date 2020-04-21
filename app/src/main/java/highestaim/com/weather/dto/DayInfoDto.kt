package highestaim.com.weather.dto

data class DayInfoDto(
    var id:Long? = null,
    val isSelected:Boolean? = false,
    var dayName: String? = "",
    var cityName: String? = "",
    var degree: Int? = null,
    var feelLikes: Int? = null,
    var humidity: Int? = null,
    var wind: Float? = null,
    var type: String? = null,
    var image: Int? = null
)