package domain.repository

import domain.model.entity.Weather
import org.example.domain.model.entity.Location

interface WeatherRepository {
    suspend fun getCurrentWeather(location: Location): Weather
}