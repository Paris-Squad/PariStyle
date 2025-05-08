package domain.model.entity.weather

import org.example.domain.model.entity.weather.WeatherCondition

data class Weather(
    val temperature: Double,
    val temperatureUnit: String,
    val weatherCondition: WeatherCondition,
)

