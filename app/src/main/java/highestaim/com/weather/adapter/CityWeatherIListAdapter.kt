package highestaim.com.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import highestaim.com.pixomaticprojectweather.R
import highestaim.com.weather.dto.DayInfoDto
import kotlinx.android.synthetic.main.city_item_view.view.*

class CityWeatherIListAdapter : RecyclerView.Adapter<CityWeatherIListAdapter.MyViewHolder>() {

    private val citiesInfo = arrayListOf<DayInfoDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.city_item_view, parent, false)
        )
    }

    override fun getItemCount() = citiesInfo.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cityInfo = citiesInfo[position]

        holder.cityName?.text = cityInfo.dayName
        holder.degreeTextView?.text = cityInfo.degree.toString()
        holder.feelLikesTextView?.text = cityInfo.feelLikes.toString()
        holder.humidityTextView?.text = cityInfo.humidity.toString() + " %"

        holder.weatherImageView.setImageDrawable(cityInfo.image?.let {
            getDrawable(
                holder.itemView.context,
                it
            )
        })

    }


    fun updateData(citiesInfo: List<DayInfoDto>) {
        this.citiesInfo.clear()
        this.citiesInfo.addAll(citiesInfo)
        notifyDataSetChanged()
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val degreeTextView: AppCompatTextView? = itemView.degreeTextView
        val humidityTextView: AppCompatTextView? = itemView.humidityTextView
        val feelLikesTextView: AppCompatTextView? = itemView.feelsLikeTextView
        val cityName: AppCompatTextView? = itemView.dayName
        val weatherImageView: AppCompatImageView = itemView.weatherImageView
        private val item: RelativeLayout? = itemView.item

        fun setBackgroundRed() {
            item?.setBackgroundResource(R.drawable.day_item_background)
        }

        fun setBackgroundBlue() {
            item?.setBackgroundResource(R.drawable.day_item_background_blue)
        }

        fun setBackgroundYellow() {
            item?.setBackgroundResource(R.drawable.day_item_background_yellow)
        }

        fun getSelectedItem(): DayInfoDto {
            return citiesInfo[layoutPosition]
        }
    }
}