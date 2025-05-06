package domain.usecase


import domain.model.entity.Weather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.domain.model.entity.ClothingType
import org.example.domain.model.entity.Location
import org.example.domain.model.entity.WeatherCondition
import org.example.domain.usecase.GetClothingRecommendationUseCase
import org.example.domain.usecase.GetWeatherUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GetClothingRecommendationUseCaseTest {
    private val getWeatherUseCase: GetWeatherUseCase = mockk()
    private lateinit var getClothingRecommendationUseCase: GetClothingRecommendationUseCase

    @BeforeEach
    fun setUp() {
        getClothingRecommendationUseCase = GetClothingRecommendationUseCase(getWeatherUseCase)
    }

    @Test
    fun `returns RAINCOAT for cold rainy weather`() = runTest {
        val weather = Weather(0.0,"c",WeatherCondition.SLIGHT_RAIN)
        coEvery{getWeatherUseCase.getWeather()} returns  weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertEquals(ClothingType.RAINCOAT, result.clothingType)
    }
    @Test
    fun `returns SHIRT for mild overcast weather`() = runTest {
        val weather= Weather(15.0,"c",WeatherCondition.PARTLY_CLOUDY)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result= getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertEquals(ClothingType.SHIRT,result.clothingType)
    }

    @Test
    fun `returns T_SHIRT for hot sunny weather`() = runTest {
        val weather = Weather(30.0,"c", WeatherCondition.MAINLY_CLEAR)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertEquals(ClothingType.T_SHIRT,result.clothingType)
    }
    @Test
    fun `returns RAINCOAT for drizzle and 10 degrees`() = runTest {
        val weather = Weather(10.0, "c", WeatherCondition.DRIZZLE)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()
        assertEquals(ClothingType.RAINCOAT, result.clothingType)
    }
    @Test
    fun `returns UMBRELLA for rainy weather in location`() = runTest {
        val location = Location(latitude = 40.7128, longitude = -74.0060)
        val weather = Weather(30.0,"c", WeatherCondition.MODERATE_RAIN_SHOWERS)
        coEvery { getWeatherUseCase.getLocationWeather(location) } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(location)

        assertEquals(ClothingType.UMBRELLA,result.clothingType)
        assertEquals("Foldable umbrella", result.description)
    }
    @Test
    fun `returns first matching item when multiple clothing items apply`() = runTest {
        val location = Location(latitude = 0.0, longitude = 0.0)
        val weather = Weather(20.0,"c", WeatherCondition.MODERATE_RAIN_SHOWERS)
        coEvery { getWeatherUseCase.getLocationWeather(location) } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(location)

        assertEquals(ClothingType.RAINCOAT,result.clothingType)
    }

    @Test
    fun `returns RAINCOAT for negative temperature within raincoat range`() = runTest {
        val weather = Weather(-5.0,"c", WeatherCondition.MODERATE_RAIN_SHOWERS)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertEquals(ClothingType.RAINCOAT,result.clothingType)
    }

    @Test
    fun `returns UNKNOWN when no clothing item matches`() = runTest {
        val weather = Weather(70.0,"c", WeatherCondition.UNKNOWN)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertEquals(ClothingType.UNKNOWN,result.clothingType)
    }
    @Test
    fun `returns T_SHIRT at upper bound of its temperature range`() = runTest {
        val weather = Weather(40.0, "c", WeatherCondition.MAINLY_CLEAR)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertEquals(ClothingType.T_SHIRT, result.clothingType)
    }

    @Test
    fun `returns SHIRT at lower bound of its temperature range`() = runTest {
        val weather = Weather(15.0, "c", WeatherCondition.OVERCAST)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertEquals(ClothingType.SHIRT, result.clothingType)
    }
}