package highestaim.com.weather.DI

import highestaim.com.weather.repository.WeatherRepository
import highestaim.com.weather.service.WeatherInterface
import highestaim.com.weather.viewmodles.WeatherViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appRepositories: Module = module {
    single { WeatherRepository(get()) }
}


val Services: Module = module {
    single { WeatherInterface.create() }
}

val appViewModels: Module = module {
    viewModel { WeatherViewModel(get()) }
}