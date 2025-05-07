package domain.repository

import domain.model.entity.weather.Weather
import org.example.domain.model.entity.weather.Location

interface WeatherRepository {
    suspend fun getLocationCurrentWeather(location: Location): Weather
    suspend fun getCurrentWeather(): Weather
}