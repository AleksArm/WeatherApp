package highestaim.com.weather.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import highestaim.com.pixomaticprojectweather.R
import highestaim.com.weather.ui.fragment.WeatherFragment
import highestaim.com.weather.utils.replaceFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        replaceFragment(fragment = WeatherFragment(), addBackStack = false)
    }
}