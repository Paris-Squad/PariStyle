package data.repository

import com.google.common.truth.Truth.assertThat
import org.example.data.repository.ClothingRepositoryImpl
import org.example.domain.model.entity.cloth.enums.ClothingCategory
import org.example.domain.model.entity.cloth.enums.ClothingType
import org.example.domain.model.entity.weather.WeatherCondition
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ClothingRepositoryImplTest {

    private lateinit var repository: ClothingRepositoryImpl

    @BeforeEach
    fun setup() {
        repository = ClothingRepositoryImpl()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should return non-empty list when weather is warm and category is upper body`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.UPPER_BODY,
            setOf(WeatherCondition.CLEAR_SKY),
            25.0
        )
        assertThat(result).isNotEmpty()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should return items of correct category when weather is warm and category is upper body`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.UPPER_BODY,
            setOf(WeatherCondition.CLEAR_SKY),
            25.0
        )
        assertThat(result.all { it.clothingType.category == ClothingCategory.UPPER_BODY }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should include t-shirts when weather is warm`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.UPPER_BODY,
            setOf(WeatherCondition.CLEAR_SKY),
            25.0
        )
        assertThat(result.any { it.clothingType == ClothingType.T_SHIRT }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should include shirts when weather is warm`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.UPPER_BODY,
            setOf(WeatherCondition.CLEAR_SKY),
            25.0
        )
        assertThat(result.any { it.clothingType == ClothingType.SHIRT }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should exclude heavy jackets when weather is warm`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.UPPER_BODY,
            setOf(WeatherCondition.CLEAR_SKY),
            25.0
        )
        assertThat(result.none { it.clothingType == ClothingType.HEAVY_JACKET }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should return non-empty list when weather is rainy and category is lower body`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.LOWER_BODY,
            setOf(WeatherCondition.SLIGHT_RAIN),
            20.0
        )
        assertThat(result).isNotEmpty()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should return items of correct category when weather is rainy and category is lower body`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.LOWER_BODY,
            setOf(WeatherCondition.SLIGHT_RAIN),
            20.0
        )
        assertThat(result.all { it.clothingType.category == ClothingCategory.LOWER_BODY }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should include jeans when weather is rainy`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.LOWER_BODY,
            setOf(WeatherCondition.SLIGHT_RAIN),
            20.0
        )
        assertThat(result.any { it.clothingType == ClothingType.JEANS }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should exclude shorts when weather is rainy`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.LOWER_BODY,
            setOf(WeatherCondition.SLIGHT_RAIN),
            20.0
        )
        assertThat(result.none { it.clothingType == ClothingType.SHORTS }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should return non-empty list when weather is cold and category is accessory`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.ACCESSORY,
            setOf(WeatherCondition.CLEAR_SKY, WeatherCondition.SLIGHT_SNOW_FALL),
            0.0
        )
        assertThat(result).isNotEmpty()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should return items of correct category when weather is cold and category is accessory`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.ACCESSORY,
            setOf(WeatherCondition.CLEAR_SKY, WeatherCondition.SLIGHT_SNOW_FALL),
            0.0
        )
        assertThat(result.all { it.clothingType.category == ClothingCategory.ACCESSORY }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should include gloves when weather is cold`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.ACCESSORY,
            setOf(WeatherCondition.CLEAR_SKY, WeatherCondition.SLIGHT_SNOW_FALL),
            0.0
        )
        assertThat(result.any { it.clothingType == ClothingType.GLOVES }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should include scarves when weather is cold`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.ACCESSORY,
            setOf(WeatherCondition.CLEAR_SKY, WeatherCondition.SLIGHT_SNOW_FALL),
            0.0
        )
        assertThat(result.any { it.clothingType == ClothingType.SCARF }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should exclude sunglasses when weather is cold`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.ACCESSORY,
            setOf(WeatherCondition.CLEAR_SKY, WeatherCondition.SLIGHT_SNOW_FALL),
            0.0
        )
        assertThat(result.none { it.clothingType == ClothingType.SUNGLASSES }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should return empty list when conditions are extreme`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.UPPER_BODY,
            setOf(WeatherCondition.VIOLENT_RAIN_SHOWERS),
            50.0
        )
        assertThat(result).isEmpty()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should return items matching weather conditions when multiple conditions are provided`() {
        val weatherConditions = setOf(
            WeatherCondition.DRIZZLE,
            WeatherCondition.SLIGHT_RAIN,
            WeatherCondition.MODERATE_RAIN
        )
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.UPPER_BODY,
            weatherConditions,
            20.0
        )
        assertThat(result.all { clothingItem ->
            clothingItem.suitableConditions.any { it in weatherConditions }
        }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should include raincoat when weather is rainy`() {
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.UPPER_BODY,
            setOf(WeatherCondition.DRIZZLE, WeatherCondition.SLIGHT_RAIN, WeatherCondition.MODERATE_RAIN),
            20.0
        )
        assertThat(result.any { it.clothingType == ClothingType.RAINCOAT }).isTrue()
    }

    @Test
    fun `getClothingItemsByCategoryAndConditions should return items within temperature range when temperature is provided`() {
        val temperature = 22.0
        val result = repository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.UPPER_BODY,
            setOf(WeatherCondition.CLEAR_SKY),
            temperature
        )
        assertThat(result.all { clothingItem ->
            temperature in clothingItem.suitableTemperatureRange.min..clothingItem.suitableTemperatureRange.max
        }).isTrue()
    }
}