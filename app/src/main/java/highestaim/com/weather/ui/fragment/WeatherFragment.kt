package highestaim.com.weather.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.Observer
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import highestaim.com.pixomaticprojectweather.R
import highestaim.com.weather.adapter.CityWeatherIListAdapter
import highestaim.com.weather.dto.DayInfoDto
import highestaim.com.weather.utils.Converter
import highestaim.com.weather.utils.WeaklyMockData
import highestaim.com.weather.utils.onClick
import highestaim.com.weather.utils.replaceFragment
import highestaim.com.weather.viewmodles.WeatherViewModel
import kotlinx.android.synthetic.main.weather_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : BaseFragment(),
    DiscreteScrollView.ScrollStateChangeListener<CityWeatherIListAdapter.MyViewHolder>,
    DiscreteScrollView.OnItemChangedListener<CityWeatherIListAdapter.MyViewHolder> {

    private val weatherViewModel: WeatherViewModel? by viewModel()

    private val cityWeatherIListAdapter = CityWeatherIListAdapter()


    override fun getLayoutId() = R.layout.weather_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnFloatingButtonClickListener()
        initWeaklyWeatherInfoRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        getCurrentWeatherInfo()
        setWeaklyWeatherInfo()
    }

    private fun initWeaklyWeatherInfoRecyclerView() {
        cityListHorizontalCarouselRecyclerView?.adapter = cityWeatherIListAdapter

        cityListHorizontalCarouselRecyclerView.setSlideOnFling(true)

        cityListHorizontalCarouselRecyclerView.addOnItemChangedListener(this)

        cityListHorizontalCarouselRecyclerView.addScrollStateChangeListener(this)

        cityListHorizontalCarouselRecyclerView.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build()
        )
    }


    private fun setOnFloatingButtonClickListener() {
        addNewCity?.onClick {
            openAddCityFragment()
        }
    }

    private fun openAddCityFragment() {
        replaceFragment(AddCityFragment(), true)
    }


    private fun getCurrentWeatherInfo() {
        weatherViewModel?.getWeather("Yerevan")?.observe(viewLifecycleOwner, Observer {
            it?.weather?.get(0)?.main?.let { it1 ->
                Converter.weatherInfoToDayInfoDto(
                    it,
                    it1
                )
            }?.let { it2 -> setInfoInMain(it2) }
        })
    }


    private fun setWeaklyWeatherInfo() {
        //here we use mock data because we do not have a weakly weather info,but current day will be actual data
        cityWeatherIListAdapter.updateData(WeaklyMockData.getWeaklyMockData())
    }

    private fun setInfoInMain(info: DayInfoDto) {
        degreeTextView?.text = info.degree.toString()
        humidityTextView?.text = getString(R.string.humidity, info.humidity.toString() + " %")
        windTextView?.text = getString(R.string.wind, info.wind.toString())
        weatherType?.text = info.type
        weatherImage?.setImageDrawable(info.image?.let {
            context?.let { it1 ->
                getDrawable(
                    it1,
                    it
                )
            }
        })
    }

    override fun onCurrentItemChanged(viewHolder: CityWeatherIListAdapter.MyViewHolder?, p1: Int) {
        viewHolder?.setBackgroundRed()
        viewHolder?.getSelectedItem()?.let { setInfoInMain(it) }
    }

    override fun onScroll(
        p0: Float, p1: Int, p2: Int, p3: CityWeatherIListAdapter.MyViewHolder?,
        p4: CityWeatherIListAdapter.MyViewHolder?
    ) {
    }

    override fun onScrollEnd(viewHolder: CityWeatherIListAdapter.MyViewHolder, p1: Int) {
        viewHolder.setBackgroundYellow()
    }

    override fun onScrollStart(viewHolder: CityWeatherIListAdapter.MyViewHolder, p1: Int) {
        viewHolder.setBackgroundBlue()
    }

}