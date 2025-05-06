package domain.usecase


import domain.model.entity.Weather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.domain.model.entity.ClothingType
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
    fun `returns Jacket for cold snowy weather`() = runTest {
        val weather = Weather(0.0,"c",WeatherCondition.SNOW_GRAINS)
        coEvery{getWeatherUseCase.getWeather()} returns  weather

        val result = getClothingRecommendationUseCase.getClothingRecommendation()

        assertEquals(ClothingType.HEAVY_JACKET, result.type)
    }
    @Test
    fun `returns Sweater for mild sunny weather`() = runTest {
        val weather= Weather(15.0,"c",WeatherCondition.MAINLY_CLEAR)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result= getClothingRecommendationUseCase.getClothingRecommendation()

        assertEquals(ClothingType.SWEATER,result.type)
    }

    @Test
    fun `returns T-Shirt for warm sunny weather`() = runTest {
        val weather = Weather(30.0,"c", WeatherCondition.MAINLY_CLEAR)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendation()

        assertEquals(ClothingType.T_SHIRT,result.type)
    }
}