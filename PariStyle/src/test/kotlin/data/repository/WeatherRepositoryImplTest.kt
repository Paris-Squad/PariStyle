package data.repository


import data.model.CurrentWeatherDTO
import data.model.CurrentWeatherUnitsDTO
import data.model.WeatherDTO
import domain.model.entity.Weather
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.example.data.model.IpInfoDTO
import org.example.data.repository.WeatherRepositoryImpl
import org.example.domain.model.entity.Location
import org.example.domain.model.entity.WeatherCondition
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WeatherRepositoryImplTest {
    private lateinit var httpClient: HttpClient
    private lateinit var repository: WeatherRepositoryImpl
    @BeforeEach
    fun setup(){
        val mockEngine = MockEngine { request ->
            when {
                request.url.toString().contains("open-meteo") -> {
                    val weatherDTO = WeatherDTO(
                        currentWeather = CurrentWeatherDTO(temperature = 21.5, weatherCode = 3),
                        currentWeatherUnits = CurrentWeatherUnitsDTO(temperature = "°C")
                    )
                    respond(
                        content = Json.encodeToString(weatherDTO),
                        status = HttpStatusCode.OK,
                        headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
                    )
                }

                request.url.toString().contains("ip-api") -> {
                    val ipInfoDTO = IpInfoDTO(latitude = 10.0, longitude = 20.0)
                    respond(
                        content = Json.encodeToString(ipInfoDTO),
                        status = HttpStatusCode.OK,
                        headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
                    )
                }

                else -> {
                    respond(
                        content = "Error",
                        status = HttpStatusCode.BadRequest
                    )
                }
            }
        }

        httpClient = HttpClient(mockEngine) {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json()
            }
        }
        repository = WeatherRepositoryImpl(httpClient)
    }

    @Test
    fun `getCurrentWeather should return weather data when API call is successful`() = runTest{
        val location = Location(10.0, 20.0)

        val expectedWeather = Weather(
            temperature = 21.5,
            unit = "°C",
            weatherCondition = WeatherCondition.OVERCAST
        )

        val result = repository.getLocationCurrentWeather(location)

        assertEquals(expectedWeather, result)
    }

    @Test
    fun `getCurrentWeather resolves IP and returns Weather`() = runTest {
        val expectedWeather = Weather(
            temperature = 21.5,
            unit = "°C",
            weatherCondition = WeatherCondition.OVERCAST
        )

        val result = repository.getCurrentWeather()

        assertEquals(expectedWeather, result)
    }
}