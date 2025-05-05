package domain.model.entity

import org.example.domain.model.entity.WeatherCondition

data class Weather(
    val temperature: Double,
    val weatherCondition: WeatherCondition,
)

