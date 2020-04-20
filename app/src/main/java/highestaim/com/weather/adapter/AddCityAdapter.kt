package highestaim.com.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import highestaim.com.pixomaticprojectweather.R
import highestaim.com.weather.dto.CityDto
import highestaim.com.weather.utils.onClick
import kotlinx.android.synthetic.main.add_city_search_item_view.view.*

class AddCityAdapter : RecyclerView.Adapter<AddCityAdapter.MyViewHolder>() {

    private val cities = arrayListOf<CityDto>()
    var onCityClick: ((CityDto) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.add_city_search_item_view, parent, false)
        )
    }

    override fun getItemCount() = cities.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val city = cities[position]

        holder.cityName?.text = city.name
        holder.degree?.text = city.degree.toString()

        holder.itemView.onClick {
            onCityClick?.invoke(city)
        }
    }

    fun updateData(newCities: List<CityDto>) {
        this.cities.clear()
        this.cities.addAll(newCities)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: AppCompatTextView? = itemView.cityName
        val degree: AppCompatTextView? = itemView.degreeTextView
    }

}