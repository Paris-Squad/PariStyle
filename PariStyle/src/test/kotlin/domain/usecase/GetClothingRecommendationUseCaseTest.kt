package domain.usecase


import com.google.common.truth.Truth.assertThat
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
    fun `should returns RAINCOAT when cold rainy weather`() = runTest {
        val weather = Weather(0.0,"c",WeatherCondition.SLIGHT_RAIN)
        coEvery{getWeatherUseCase.getWeather()} returns  weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.clothingType).isEqualTo(ClothingType.RAINCOAT)
    }
    @Test
    fun `should returns SHIRT when mild overcast weather`() = runTest {
        val weather= Weather(15.0,"c",WeatherCondition.PARTLY_CLOUDY)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result= getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.clothingType).isEqualTo(ClothingType.SHIRT)
    }

    @Test
    fun `should returns T_SHIRT when hot sunny weather`() = runTest {
        val weather = Weather(30.0,"c", WeatherCondition.MAINLY_CLEAR)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.clothingType).isEqualTo(ClothingType.T_SHIRT)
    }
    @Test
    fun `should returns RAINCOAT when drizzle and 10 degrees`() = runTest {
        val weather = Weather(10.0, "c", WeatherCondition.DRIZZLE)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.clothingType).isEqualTo(ClothingType.RAINCOAT)
    }
    @Test
    fun `should returns UMBRELLA when rainy weather in location`() = runTest {
        val location = Location(latitude = 40.7128, longitude = -74.0060)
        val weather = Weather(30.0,"c", WeatherCondition.MODERATE_RAIN_SHOWERS)
        coEvery { getWeatherUseCase.getLocationWeather(location) } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(location)

        assertThat(result.clothingType).isEqualTo(ClothingType.UMBRELLA)
    }
    @Test
    fun `should returns first matching item when multiple clothing items apply`() = runTest {
        val location = Location(latitude = 0.0, longitude = 0.0)
        val weather = Weather(20.0,"c", WeatherCondition.MODERATE_RAIN_SHOWERS)
        coEvery { getWeatherUseCase.getLocationWeather(location) } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(location)

        assertThat(result.clothingType).isEqualTo(ClothingType.RAINCOAT)
    }

    @Test
    fun `should returns RAINCOAT when negative temperature within raincoat range`() = runTest {
        val weather = Weather(-5.0,"c", WeatherCondition.MODERATE_RAIN_SHOWERS)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.clothingType).isEqualTo(ClothingType.RAINCOAT)
    }

    @Test
    fun `should returns UNKNOWN when no clothing item matches`() = runTest {
        val weather = Weather(70.0,"c", WeatherCondition.UNKNOWN)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.clothingType).isEqualTo(ClothingType.UNKNOWN)
    }
    @Test
    fun `should returns T_SHIRT at upper bound of its temperature range`() = runTest {
        val weather = Weather(40.0, "c", WeatherCondition.MAINLY_CLEAR)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.clothingType).isEqualTo(ClothingType.T_SHIRT)
    }

    @Test
    fun `should returns SHIRT at lower bound of its temperature range`() = runTest {
        val weather = Weather(15.0, "c", WeatherCondition.OVERCAST)
        coEvery { getWeatherUseCase.getWeather() } returns weather

        val result = getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.clothingType).isEqualTo(ClothingType.SHIRT)
    }
}