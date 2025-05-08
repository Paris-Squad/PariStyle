package domain.usecase

import com.google.common.truth.Truth.assertThat
import domain.model.entity.ClothingItem
import domain.model.entity.weather.Weather
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.example.domain.model.entity.cloth.enums.ClothingCategory
import org.example.domain.model.entity.cloth.enums.ClothingType
import org.example.domain.model.entity.weather.Location
import org.example.domain.model.entity.weather.TemperatureRange
import org.example.domain.model.entity.weather.WeatherCondition
import org.example.domain.model.exception.PariStyleException
import org.example.domain.repository.ClothingRepository
import org.example.domain.usecase.GetClothingRecommendationUseCase
import org.example.domain.usecase.GetWeatherUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetClothingRecommendationUseCaseTest {

    private lateinit var getWeatherUseCase: GetWeatherUseCase
    private lateinit var clothingRepository: ClothingRepository
    private lateinit var useCase: GetClothingRecommendationUseCase

    @BeforeEach
    fun setup() {
        getWeatherUseCase = mockk()
        clothingRepository = mockk()
        useCase = GetClothingRecommendationUseCase(getWeatherUseCase, clothingRepository)
    }

    @Test
    fun `getCurrentWeather should verify getWeather is called exactly once`() = runBlocking {
        val mockWeather = mockWeather()
        setupMockResponses(mockWeather)

        useCase.getClothingRecommendationForCurrentWeather()

        coVerify(exactly = 1) { getWeatherUseCase.getWeather() }
    }

    @Test
    fun `getCurrentWeather should return correct top item`() = runBlocking {
        val mockWeather = mockWeather()
        val topItem = mockClothingItem(ClothingCategory.UPPER_BODY)
        setupMockResponses(mockWeather, topItem = topItem)

        val result = useCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.topItem).isEqualTo(topItem)
    }

    @Test
    fun `getCurrentWeather should return correct bottom item`() = runBlocking {
        val mockWeather = mockWeather()
        val bottomItem = mockClothingItem(ClothingCategory.LOWER_BODY)
        setupMockResponses(mockWeather, bottomItem = bottomItem)

        val result = useCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.bottomItem).isEqualTo(bottomItem)
    }

    @Test
    fun `getCurrentWeather should return correct accessory item`() = runBlocking {
        val mockWeather = mockWeather()
        val accessoryItem = mockClothingItem(ClothingCategory.ACCESSORY)
        setupMockResponses(mockWeather, accessoryItem = accessoryItem)

        val result = useCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.accessoryItem).isEqualTo(accessoryItem)
    }

    @Test
    fun `getLocationWeather should verify getLocationWeather is called exactly once`() = runBlocking {
        val location = Location(latitude = 48.8566, longitude = 2.3522)
        val mockWeather = mockWeather()
        setupMockLocationResponses(location, mockWeather)

        useCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(location)

        coVerify(exactly = 1) { getWeatherUseCase.getLocationWeather(location) }
    }

    @Test
    fun `getLocationWeather should return correct top item`() = runBlocking {
        val location = Location(latitude = 48.8566, longitude = 2.3522)
        val mockWeather = mockWeather()
        val topItem = mockClothingItem(ClothingCategory.UPPER_BODY)
        setupMockLocationResponses(location, mockWeather, topItem = topItem)

        val result = useCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(location)

        assertThat(result.topItem).isEqualTo(topItem)
    }

    @Test
    fun `getLocationWeather should return correct bottom item`() = runBlocking {
        val location = Location(latitude = 48.8566, longitude = 2.3522)
        val mockWeather = mockWeather()
        val bottomItem = mockClothingItem(ClothingCategory.LOWER_BODY)
        setupMockLocationResponses(location, mockWeather, bottomItem = bottomItem)

        val result = useCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(location)

        assertThat(result.bottomItem).isEqualTo(bottomItem)
    }

    @Test
    fun `getLocationWeather should return correct accessory item`() = runBlocking {
        val location = Location(latitude = 48.8566, longitude = 2.3522)
        val mockWeather = mockWeather()
        val accessoryItem = mockClothingItem(ClothingCategory.ACCESSORY)
        setupMockLocationResponses(location, mockWeather, accessoryItem = accessoryItem)

        val result = useCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(location)

        assertThat(result.accessoryItem).isEqualTo(accessoryItem)
    }

    @Test
    fun `getWeather should propagate exception when weather service fails`():Unit = runBlocking {
        coEvery { getWeatherUseCase.getWeather() } throws RuntimeException("Weather service error")

        assertThrows<RuntimeException> { useCase.getClothingRecommendationForCurrentWeather() }
    }

    @Test
    fun `getLocationWeather should propagate exception when weather service fails`():Unit = runBlocking {
        val location = Location(latitude = 48.8566, longitude = 2.3522)
        coEvery { getWeatherUseCase.getLocationWeather(location) } throws RuntimeException("Weather service error")

        assertThrows<RuntimeException> { useCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(location) }
    }

    @Test
    fun `toLocation should default both values to zero when latitude and longitude are null`():Unit = runBlocking {
        val location = Location(latitude = 0.0, longitude = 0.0)
        val mockWeather = mockWeather()

        coEvery { getWeatherUseCase.getLocationWeather(location) } returns mockWeather
        coEvery { clothingRepository.getClothingItemsByCategoryAndConditions(any(), any(), any()) } returns emptyList()

        assertThrows<PariStyleException.NotFoundException> {
            useCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(location)
        }
    }

    @Test
    fun `getClothingRecommendation should throw NotFoundException when no bottom items found`():Unit = runBlocking {
        val mockWeather = mockWeather()
        val topItem = mockClothingItem(ClothingCategory.UPPER_BODY)

        coEvery { getWeatherUseCase.getWeather() } returns mockWeather
        coEvery {
            clothingRepository.getClothingItemsByCategoryAndConditions(
                ClothingCategory.UPPER_BODY,
                any(),
                any()
            )
        } returns listOf(topItem)
        coEvery {
            clothingRepository.getClothingItemsByCategoryAndConditions(
                ClothingCategory.LOWER_BODY,
                any(),
                any()
            )
        } returns emptyList()
        coEvery {
            clothingRepository.getClothingItemsByCategoryAndConditions(
                ClothingCategory.ACCESSORY,
                any(),
                any()
            )
        } returns emptyList()

        assertThrows<PariStyleException.NotFoundException> {
            useCase.getClothingRecommendationForCurrentWeather()
        }
    }

    @Test
    fun `getClothingRecommendation should return null accessoryItem when no accessories found`() = runBlocking {
        val mockWeather = mockWeather()
        val topItem = mockClothingItem(ClothingCategory.UPPER_BODY)
        val bottomItem = mockClothingItem(ClothingCategory.LOWER_BODY)

        coEvery { getWeatherUseCase.getWeather() } returns mockWeather
        coEvery {
            clothingRepository.getClothingItemsByCategoryAndConditions(
                ClothingCategory.UPPER_BODY,
                any(),
                any()
            )
        } returns listOf(topItem)
        coEvery {
            clothingRepository.getClothingItemsByCategoryAndConditions(
                ClothingCategory.LOWER_BODY,
                any(),
                any()
            )
        } returns listOf(bottomItem)
        coEvery {
            clothingRepository.getClothingItemsByCategoryAndConditions(
                ClothingCategory.ACCESSORY,
                any(),
                any()
            )
        } returns emptyList()

        val result = useCase.getClothingRecommendationForCurrentWeather()

        assertThat(result.accessoryItem).isNull()
    }

    @Test
    fun `getLocationWeather should be called with correct location`() = runBlocking {
        val location = Location(latitude = 48.8566, longitude = 2.3522)
        val mockWeather = mockWeather()
        val topItem = mockClothingItem(ClothingCategory.UPPER_BODY)

        coEvery { getWeatherUseCase.getLocationWeather(location) } returns mockWeather
        coEvery {
            clothingRepository.getClothingItemsByCategoryAndConditions(any(), any(), any())
        } returns listOf(topItem)

        useCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(location)

        coVerify(exactly = 1) { getWeatherUseCase.getLocationWeather(location) }
    }

    private fun setupMockResponses(
        mockWeather: Weather,
        topItem: ClothingItem = mockClothingItem(ClothingCategory.UPPER_BODY),
        bottomItem: ClothingItem = mockClothingItem(ClothingCategory.LOWER_BODY),
        accessoryItem: ClothingItem = mockClothingItem(ClothingCategory.ACCESSORY)
    ) {
        coEvery { getWeatherUseCase.getWeather() } returns mockWeather
        setupCommonMocks(mockWeather, topItem, bottomItem, accessoryItem)
    }

    private fun setupMockLocationResponses(
        location: Location,
        mockWeather: Weather,
        topItem: ClothingItem = mockClothingItem(ClothingCategory.UPPER_BODY),
        bottomItem: ClothingItem = mockClothingItem(ClothingCategory.LOWER_BODY),
        accessoryItem: ClothingItem = mockClothingItem(ClothingCategory.ACCESSORY)
    ) {
        coEvery { getWeatherUseCase.getLocationWeather(location) } returns mockWeather
        setupCommonMocks(mockWeather, topItem, bottomItem, accessoryItem)
    }

    private fun setupCommonMocks(
        mockWeather: Weather,
        topItem: ClothingItem,
        bottomItem: ClothingItem,
        accessoryItem: ClothingItem
    ) {
        coEvery {
            clothingRepository.getClothingItemsByCategoryAndConditions(
                ClothingCategory.UPPER_BODY,
                setOf(mockWeather.weatherCondition),
                mockWeather.temperature
            )
        } returns listOf(topItem)

        coEvery {
            clothingRepository.getClothingItemsByCategoryAndConditions(
                ClothingCategory.LOWER_BODY,
                setOf(mockWeather.weatherCondition),
                mockWeather.temperature
            )
        } returns listOf(bottomItem)

        coEvery {
            clothingRepository.getClothingItemsByCategoryAndConditions(
                ClothingCategory.ACCESSORY,
                setOf(mockWeather.weatherCondition),
                mockWeather.temperature
            )
        } returns listOf(accessoryItem)
    }

    private fun mockWeather() = Weather(
        temperature = 20.0,
        weatherCondition = WeatherCondition.CLEAR_SKY,
        temperatureUnit = "c"
    )

    private fun mockClothingItem(category: ClothingCategory) = ClothingItem(
        clothingType = ClothingType.UNKNOWN,
        description = "A ${category.name} item for testing",
        suitableTemperatureRange = TemperatureRange(15.0, 25.0),
        suitableConditions = setOf(WeatherCondition.CLEAR_SKY, WeatherCondition.MAINLY_CLEAR)
    )
}