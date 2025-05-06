package domain.model.entity

import org.example.domain.model.entity.WeatherCondition

data class Weather(
    val temperature: Double,
    val temperatureUnit: String,
    val weatherCondition: WeatherCondition,
)

