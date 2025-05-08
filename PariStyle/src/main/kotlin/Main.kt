package org.example

import di.appModule
import org.example.presentation.view.WeatherRecommendationCLI
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

fun main() {
    startKoin { modules(appModule) }

    val cli by inject<WeatherRecommendationCLI>(WeatherRecommendationCLI::class.java)
    cli.start()
}
