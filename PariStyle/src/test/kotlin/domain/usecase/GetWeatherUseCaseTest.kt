package domain.usecase

import com.google.common.truth.Truth.assertThat
import domain.model.entity.weather.Weather
import domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.domain.model.entity.weather.Location
import org.example.domain.model.entity.weather.WeatherCondition
import org.example.domain.usecase.GetWeatherUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetWeatherUseCaseTest {
    private lateinit var getWeatherUseCase: GetWeatherUseCase
    private val weatherRepository:WeatherRepository = mockk()

    @BeforeEach
    fun setUp() {
        getWeatherUseCase = GetWeatherUseCase(weatherRepository)
    }

    @Test
    fun `getLocationWeather should return weather data when repository returns data`()  = runTest {
        val location = Location(latitude = 40.7128, longitude = -74.0060)
        val expectedWeather = Weather(53.0, "c",WeatherCondition.CLEAR_SKY)
        coEvery { weatherRepository.getLocationCurrentWeather(location) } returns expectedWeather

        val result = getWeatherUseCase.getLocationWeather(location)

        assertThat(result).isEqualTo(expectedWeather)
    }

    @Test
    fun `getWeather should return weather data when repository returns data`()  = runTest {
        val expectedWeather = Weather(53.0, "c",WeatherCondition.CLEAR_SKY)
        coEvery { weatherRepository.getCurrentWeather() } returns expectedWeather

        val result = getWeatherUseCase.getWeather()

        assertThat(result).isEqualTo(expectedWeather)
    }
}