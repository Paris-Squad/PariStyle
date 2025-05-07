package org.example.data.repository

import data.model.WeatherDTO
import domain.model.entity.weather.Weather
import domain.repository.WeatherRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import org.example.data.mapper.toLocation
import org.example.data.mapper.toWeather
import org.example.data.model.IpInfoDTO
import org.example.domain.model.entity.weather.Location
import org.example.domain.model.exception.PariStyleException

class WeatherRepositoryImpl(private val httpClient: HttpClient) : WeatherRepository {

    override suspend fun getLocationCurrentWeather(location: Location): Weather {
        return apiCall {
            val url =
                "$OPEN_METEO_URL?latitude=${location.latitude}&longitude=${location.longitude}&current_weather=true"
            val response = httpClient.get(url)
            val weatherDTO = response.body<WeatherDTO>()
            weatherDTO.toWeather()
        }
    }

    override suspend fun getCurrentWeather(): Weather {
        return apiCall {
            val location = getLocation()
            val url =
                "$OPEN_METEO_URL?latitude=${location.latitude}&longitude=${location.longitude}&current_weather=true"
            val response = httpClient.get(url)
            val weatherDTO = response.body<WeatherDTO>()
            weatherDTO.toWeather()
        }
    }

    private suspend fun getLocation(): Location {
        return apiCall {
            val ipResponse = httpClient.get(IPIFY_URL).body<String>()
            val url = "$IPI_API_URL$ipResponse?fields=status,country,regionName,city,lat,lon,timezone"
            val response = httpClient.get(url)
            val ipInfoDTO = response.body<IpInfoDTO>()
            ipInfoDTO.toLocation()
        }
    }

    private suspend fun <T> apiCall(block: suspend () -> T): T {
        return try {
            block()
        } catch (e: Exception) {
            when (e) {
                is ResponseException -> {
                    val errorCode = e.response.status.value
                    throw PariStyleException.NetWorkException("response error: $errorCode - ${e.message}")
                }

                else -> throw PariStyleException.NetWorkException("response error: ${e.message}")
            }
        }
    }

    companion object {
        private const val OPEN_METEO_URL = "https://api.open-meteo.com/v1/forecast"
        private const val IPIFY_URL = "https://api.ipify.org"
        private const val IPI_API_URL = "http://ip-api.com/json/"
    }
}