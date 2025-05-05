package org.example.data.repository

import domain.model.entity.Weather
import domain.repository.WeatherRepository
import org.example.domain.model.entity.Location

class WeatherRepositoryImpl() : WeatherRepository {
    override suspend fun getCurrentWeather(location: Location): Weather {
        TODO("Not yet implemented")
    }
}