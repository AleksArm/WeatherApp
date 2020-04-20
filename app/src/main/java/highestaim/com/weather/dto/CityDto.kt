package highestaim.com.weather.dto

data class CityDto(
    var id:Int? = null,
    var name: String? = "",
    var degree: Int? = 0,
    var feelLikes: Int? = 0,
    var humidity: Int? = 0,
    var wind: Float? = 0.0f
)