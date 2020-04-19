package highestaim.com.weather.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClass  {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    val retroInstance: Retrofit
        get() {
            val builder = GsonBuilder()
            builder.excludeFieldsWithoutExposeAnnotation()
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
        }
}