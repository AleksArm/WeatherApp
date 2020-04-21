package highestaim.com.weather.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import highestaim.com.pixomaticprojectweather.R
import highestaim.com.weather.adapter.AddCityAdapter
import highestaim.com.weather.adapter.RecentlySearchAdapter
import highestaim.com.weather.dto.CityDto
import highestaim.com.weather.service.PreferenceService.Companion.get
import highestaim.com.weather.utils.*
import highestaim.com.weather.viewmodles.WeatherViewModel
import kotlinx.android.synthetic.main.add_country_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddCityFragment : BaseFragment() {

    private val citiesResultAdapter = AddCityAdapter()
    private val recentlySearchAdapter = RecentlySearchAdapter()

    private val weatherViewModel: WeatherViewModel? by viewModel()

    private lateinit var countDownTimerForSuggestionEditText: SimpleTimer<String>


    override fun getLayoutId() = R.layout.add_country_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        searchResultRecyclerView.initRecyclerView(context, citiesResultAdapter)

        setRecentlySearchesAdapter()

        setOnRecentlySearchesItemClickListener()

        setOnSearchClickListener()

        onCityClickListener()

        setOnClearTextClickListener()

        hideRecentlySearchesViews()

        countDownTimerForSuggestionEditText = SimpleTimer(200) {
            it?.let {
                search(it)
            }
        }
    }

    private fun hideRecentlySearchesViews() {
        val recentlySearchesCities = get().getRecentlySearchesCities()
        recentlySearchTitle?.showIf(
            recentlySearchesCities != null
                    && recentlySearchesCities.size != 0
        )
        recentlySearch?.showIf(
            recentlySearchesCities != null
                    && recentlySearchesCities.size != 0
        )
    }

    override fun onResume() {
        super.onResume()
        get().getRecentlySearchesCities()?.let { setRecentlySearchResult(it) }
    }

    private fun setOnSearchClickListener() {
        searchEditText?.onTextChanged {
            countDownTimerForSuggestionEditText.restart(it)
        }
    }

    private fun search(cityName: String) {
        if (cityName.isNotBlank()) {
            weatherViewModel?.getWeather(cityName)?.observe(viewLifecycleOwner, Observer {
                it?.let { cityWeather ->
                    setSearchResult(
                        arrayListOf(
                            CityDto(
                                name = cityWeather.name?.toUpperCase(Locale.ROOT),
                                degree = cityWeather.main?.temp?.toInt(),
                                type = it.weather?.get(0)?.main,
                                humidity = it.main?.humidity?.toInt(),
                                feelLikes = it.main?.feelsLike?.toInt(),
                                wind = it.wind?.speed?.toFloat()
                            )
                        )
                    )
                    searchResultRecyclerView?.show()
                }

            })
        } else {
            setSearchResult(arrayListOf())
        }
    }

    private fun setSearchResult(cities: ArrayList<CityDto>) {
        citiesResultAdapter.updateData(cities)
    }

    private fun setRecentlySearchResult(cities: ArrayList<CityDto>) {
        recentlySearchAdapter.updateData(cities.reversed())
    }

    private fun setRecentlySearchesAdapter() {
        recentlySearch?.initRecyclerView(context, recentlySearchAdapter)
    }

    private fun setOnRecentlySearchesItemClickListener() {
        recentlySearchAdapter.onCityClick = { city ->
            city.name?.let {
                weatherViewModel?.getWeather(it)?.observe(viewLifecycleOwner, Observer {
                    get().putSelectedCity(city)
                    activity?.onBackPressed()
                })
            }
        }
    }

    private fun onCityClickListener() {
        citiesResultAdapter.onCityClick = {
            get().putSelectedCity(it)
            setSelectedCityToRecently(it)
            activity?.onBackPressed()
        }
    }

    private fun setOnClearTextClickListener() {
        clearTextImageBtn?.onClick {
            searchEditText?.setText("")
        }
    }

    private fun setSelectedCityToRecently(cityDto: CityDto) {
        val cities = get().getRecentlySearchesCities()
        cities?.add(cityDto)
        cities?.let { get().saveRecentlySearchesCities(it) }
    }

}