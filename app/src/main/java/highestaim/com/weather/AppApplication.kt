package highestaim.com.weather

import android.app.Application
import highestaim.com.weather.DI.Services
import highestaim.com.weather.DI.appRepositories
import highestaim.com.weather.DI.appViewModels
import highestaim.com.weather.service.PreferenceService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(appRepositories, appViewModels, Services))
        }

        PreferenceService.get().injectContext(this)

    }

}