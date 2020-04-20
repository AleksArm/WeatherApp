package highestaim.com.weather.utils

import java.util.*

object Utils {
    fun getCurrentDay(): Int {
        val calendar: Calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}