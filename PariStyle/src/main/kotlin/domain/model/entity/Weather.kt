package domain.model.entity

import org.example.domain.model.entity.WeatherCondition

data class Weather(
    val temperature: Double,
    val unit: String,
    val weatherCondition: WeatherCondition,
)

