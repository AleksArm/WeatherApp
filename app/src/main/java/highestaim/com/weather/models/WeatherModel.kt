package highestaim.com.weather.models


import com.google.gson.annotations.SerializedName

data class WeatherModel(

    @SerializedName("base")
    val base: String?, // stations

    @SerializedName("clouds")
    val clouds: Clouds?,

    @SerializedName("cod")
    val cod: Int?, // 200

    @SerializedName("coord")
    val coord: Coord?,

    @SerializedName("dt")
    val dt: Double?, // 1587237891

    @SerializedName("id")
    val id: Long?, // 616052

    @SerializedName("main")
    val main: Main?,

    @SerializedName("name")
    val name: String?, // Yerevan

    @SerializedName("sys")
    val sys: Sys?,

    @SerializedName("timezone")
    val timezone: Int?, // 14400

    @SerializedName("visibility")
    val visibility: Int?, // 10000

    @SerializedName("weather")
    val weather: List<Weather?>?,

    @SerializedName("wind")
    val wind: Wind?
) {
    data class Clouds(
        @SerializedName("all")
        val all: Double? // 69
    )

    data class Coord(
        @SerializedName("lat")
        val lat: Double?, // 40.18

        @SerializedName("lon")
        val lon: Double? // 44.51
    )

    data class Main(
        @SerializedName("feels_like")
        val feelsLike: Double?, // 53.67

        @SerializedName("humidity")
        val humidity: Double?, // 47

        @SerializedName("pressure")
        val pressure: Double?, // 1013

        @SerializedName("temp")
        val temp: Double?, // 60.94

        @SerializedName("temp_max")
        val tempMax: Double?, // 64.4

        @SerializedName("temp_min")
        val tempMin: Double? // 59
    )

    data class Sys(
        @SerializedName("country")
        val country: String?, // AM

        @SerializedName("id")
        val id: Long?, // 8851

        @SerializedName("sunrise")
        val sunrise: Double?, // 1587176337

        @SerializedName("sunset")
        val sunset: Double?, // 1587224587

        @SerializedName("type")
        val type: Double? // 1
    )

    data class Weather(
        @SerializedName("description")
        val description: String?, // broken clouds

        @SerializedName("icon")
        val icon: String?, // 04n

        @SerializedName("id")
        val id: Long?, // 803

        @SerializedName("main")
        val main: String? // Clouds
    )

    data class Wind(
        @SerializedName("deg")
        val deg: Double?, // 30

        @SerializedName("speed")
        val speed: Double? // 9.17
    )
}