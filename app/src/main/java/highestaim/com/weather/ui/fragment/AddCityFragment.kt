package highestaim.com.weather.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import highestaim.com.pixomaticprojectweather.R
import highestaim.com.weather.adapter.AddCityAdapter
import highestaim.com.weather.dto.CityDto
import highestaim.com.weather.utils.SimpleTimer
import highestaim.com.weather.utils.initRecyclerView
import highestaim.com.weather.utils.onTextChanged
import highestaim.com.weather.viewmodles.WeatherViewModel
import kotlinx.android.synthetic.main.add_country_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddCityFragment : BaseFragment() {

    private val citiesResultAdapter = AddCityAdapter()
    private lateinit var countDownTimerForSuggestionEditText: SimpleTimer<String>

    private val weatherViewModel: WeatherViewModel? by viewModel()

    override fun getLayoutId() = R.layout.add_country_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchResultRecyclerView.initRecyclerView(context, citiesResultAdapter)
        setOnSearchClickListener()

        countDownTimerForSuggestionEditText = SimpleTimer(200) {
            it?.let {
                search(it)
            }
        }
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
                    updateData(
                        arrayListOf(
                            CityDto(
                                name = cityWeather.name?.toUpperCase(Locale.ROOT),
                                degree = cityWeather.main?.temp?.toInt()
                            )
                        )
                    )
                }

            })
        } else {
            updateData(arrayListOf())
        }
    }

    private fun updateData(cities: ArrayList<CityDto>) {
        citiesResultAdapter.updateData(cities)
    }


}