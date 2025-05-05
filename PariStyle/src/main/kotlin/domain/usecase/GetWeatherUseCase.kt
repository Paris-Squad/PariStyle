package org.example.domain.usecase

import domain.repository.WeatherRepository
import org.example.domain.model.entity.Location

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun getWeather(location: Location) = weatherRepository.getCurrentWeather(location)
}