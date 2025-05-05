package org.example.data.repository

import data.model.WeatherDTO
import domain.model.entity.Weather
import domain.repository.WeatherRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import org.example.data.mapper.toWeather
import org.example.domain.model.entity.Location
import org.example.domain.model.exception.PariStyleException

class WeatherRepositoryImpl(private val httpClient: HttpClient) : WeatherRepository {

    override suspend fun getCurrentWeather(location: Location): Weather {
        try {
            val url = "$URL?latitude=${location.latitude}&longitude=${location.longitude}&current_weather=true"
            val response = httpClient.get(url)
            val weatherDTO = response.body<WeatherDTO>()
            return weatherDTO.toWeather()
        } catch (e: Exception) {
            when (e) {
                is ResponseException -> {
                    val errorCode = e.response.status.value
                    throw PariStyleException.NetWorkException("response error: $errorCode - ${e.message}")
                }

                else -> {
                    throw PariStyleException.NetWorkException("Network error: ${e.message}")
                }
            }
        }
    }

    companion object {
        private const val URL = "https://api.open-meteo.com/v1/forecast"
    }
}