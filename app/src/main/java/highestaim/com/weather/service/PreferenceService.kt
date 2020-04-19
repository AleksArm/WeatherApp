package highestaim.com.weather.service

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import highestaim.com.weather.dto.CityDto


class PreferenceService {

    private var savedCitySharedPreferences: SharedPreferences? = null
    private var savedCitiesSharedPreferences: SharedPreferences? = null
    private val gson by lazy { Gson() }
    private var context: Context? = null

    fun injectContext(context: Context): PreferenceService {
        if (this.context == null) {
            this.context = context
            initSharedPreferences()
        }
        return get()
    }

    private fun initSharedPreferences() {
        if (savedCitySharedPreferences == null) {
            savedCitySharedPreferences = context?.getSharedPreferences("saveUserSelectedCity",
                Context.MODE_PRIVATE
            )
        }
    }

    fun putSelectedCity(sortByRestaurantChain: CityDto) {
        savedCitySharedPreferences?.edit()?.putString("selected_city", Gson().toJson(sortByRestaurantChain))?.apply()
    }


    fun getSelectedCity(): CityDto? {
        val stringData = savedCitySharedPreferences?.getString("selected_city", null)
        if (stringData != null) {
            val type = object : TypeToken<CityDto>() {
            }.type
            return gson.fromJson(stringData, type) as CityDto

        }
        return null
    }

    fun saveLanguage(selectedLanguage: String?, selectedLanguageIndex: Int?) {
        val editor = context?.getSharedPreferences("lng", Context.MODE_PRIVATE)?.edit()
        selectedLanguageIndex?.let { editor?.putInt("saveLng", it) }
        editor?.putString("saveLngTxt", selectedLanguage)
        editor?.apply()
    }

    fun getLngCode(): Int? {
        val sharedPreferencesLanguage = context?.getSharedPreferences("lng", Context.MODE_PRIVATE)
        return sharedPreferencesLanguage?.getInt("saveLng", 0)
    }

    fun checkLanguageName(): String {
        var language = "English"
        val sharedPreferencesLanguage = context?.getSharedPreferences("lng", Context.MODE_PRIVATE)
        when (sharedPreferencesLanguage?.getInt("saveLng", -1)) {
            0, -1 ->
                language = "English"
            1 ->
                language = "Armenian"
            2 ->
                language = "Russian"
        }
        return language
    }


    fun saveCities(sortByRestaurantChain: List<CityDto>) {
        savedCitiesSharedPreferences?.edit()?.putString("cities", Gson().toJson(sortByRestaurantChain))?.apply()
    }

    fun getCities(): List<CityDto>? {
        val stringData = savedCitiesSharedPreferences?.getString("cities", null)
        if (stringData != null) {
            val type = object : TypeToken<List<CityDto>>() {
            }.type
            return gson.fromJson(stringData, type) as List<CityDto>

        }
        return null
    }


    companion object {

        private var preferenceService: PreferenceService? = null

        fun get(): PreferenceService {
            if (preferenceService == null) {
                preferenceService = PreferenceService()
            }
            return preferenceService as PreferenceService
        }
    }
}