package highestaim.com.weather.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import highestaim.com.pixomaticprojectweather.R
import highestaim.com.weather.adapter.CityWeatherIListAdapter
import highestaim.com.weather.dto.DayInfoDto
import highestaim.com.weather.service.PreferenceService.Companion.get
import highestaim.com.weather.utils.*
import highestaim.com.weather.viewmodles.WeatherViewModel
import kotlinx.android.synthetic.main.weather_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : BaseFragment(),
    DiscreteScrollView.ScrollStateChangeListener<CityWeatherIListAdapter.MyViewHolder>,
    DiscreteScrollView.OnItemChangedListener<CityWeatherIListAdapter.MyViewHolder> {


    private val cityWeatherIListAdapter = CityWeatherIListAdapter()


    override fun getLayoutId() = R.layout.weather_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnFloatingButtonClickListener()
        initWeaklyWeatherInfoRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        if (get().getSelectedCity() != null) {
            setInfoInMain(Converter.cityInfoToDayInfoDto(get().getSelectedCity()))
            hideFieldsWhenCityIsNotSelected(isCitySelected = true)
        } else {
            hideFieldsWhenCityIsNotSelected(isCitySelected = false)
            cityName?.text = getString(R.string.select_city_for_view_weather_info)
        }

        cityListHorizontalCarouselRecyclerView.scrollToPosition(3)
        //weakly info set from mock data,you can set real data from any api
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


    private fun setWeaklyWeatherInfo() {
        //here we use mock data because we do not have a weakly weather info,but current day will be actual data
        cityWeatherIListAdapter.updateData(WeaklyMockData.getWeaklyMockData())
    }

    private fun setInfoInMain(info: DayInfoDto) {
        degreeTextView?.text = info.degree.toString()
        cityName?.text = info.cityName.toString()
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

    private fun hideFieldsWhenCityIsNotSelected(isCitySelected: Boolean) {
        humidityTextView?.showIf(isCitySelected)
        celsius?.showIf(isCitySelected)
        humidityTextView?.showIf(isCitySelected)
        windTextView?.showIf(isCitySelected)
        weatherImage?.showIf(isCitySelected)
    }

    override fun onCurrentItemChanged(viewHolder: CityWeatherIListAdapter.MyViewHolder?, p1: Int) {
        viewHolder?.setBackgroundRed()
        //if you open this commented  line,then value of days weather will be shown on main
        //viewHolder?.getSelectedItem()?.let { setInfoInMain(it) }
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