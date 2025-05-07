package org.example.data.repository

import domain.model.entity.ClothingItem
import org.example.domain.model.entity.weather.TemperatureRange
import org.example.domain.model.entity.cloth.enums.ClothingCategory
import org.example.domain.model.entity.cloth.enums.ClothingType
import org.example.domain.model.entity.weather.WeatherCondition
import org.example.domain.repository.ClothingRepository

class ClothingRepositoryImpl : ClothingRepository {
    override fun getClothingItemsByCategoryAndConditions(
        category: ClothingCategory,
        weatherConditions: Set<WeatherCondition>,
        temperature: Double
    ): List<ClothingItem> {
        return clothingItems.filter { clothingItem ->
            clothingItem.clothingType.category == category &&
                    clothingItem.suitableConditions.any { it in weatherConditions } &&
                    temperature in clothingItem.suitableTemperatureRange.min..clothingItem.suitableTemperatureRange.max
        }
    }

    companion object {
        private val clothingItems = listOf(
            ClothingItem(
                clothingType = ClothingType.T_SHIRT,
                description = "Light cotton T-shirt",
                suitableTemperatureRange = TemperatureRange(20.0, 40.0),
                suitableConditions = setOf(
                    WeatherCondition.CLEAR_SKY,
                    WeatherCondition.MAINLY_CLEAR,
                    WeatherCondition.PARTLY_CLOUDY
                )
            ),
            ClothingItem(
                clothingType = ClothingType.SHIRT,
                description = "Regular long-sleeve shirt",
                suitableTemperatureRange = TemperatureRange(15.0, 25.0),
                suitableConditions = setOf(
                    WeatherCondition.CLEAR_SKY,
                    WeatherCondition.MAINLY_CLEAR,
                    WeatherCondition.PARTLY_CLOUDY,
                    WeatherCondition.OVERCAST
                )
            ),
            ClothingItem(
                clothingType = ClothingType.SWEATER,
                description = "Warm wool sweater",
                suitableTemperatureRange = TemperatureRange(5.0, 15.0),
                suitableConditions = setOf(
                    WeatherCondition.OVERCAST,
                    WeatherCondition.FOG,
                    WeatherCondition.PARTLY_CLOUDY
                )
            ),
            ClothingItem(
                clothingType = ClothingType.RAINCOAT,
                description = "Waterproof raincoat",
                suitableTemperatureRange = TemperatureRange(-5.0, 25.0),
                suitableConditions = setOf(
                    WeatherCondition.DRIZZLE,
                    WeatherCondition.LIGHT_FREEZING_DRIZZLE,
                    WeatherCondition.SLIGHT_RAIN,
                    WeatherCondition.MODERATE_RAIN,
                    WeatherCondition.HEAVY_INTENSITY_RAIN
                )
            ),
            ClothingItem(
                clothingType = ClothingType.HEAVY_JACKET,
                description = "Insulated winter jacket",
                suitableTemperatureRange = TemperatureRange(-20.0, 5.0),
                suitableConditions = setOf(
                    WeatherCondition.CLEAR_SKY,
                    WeatherCondition.MAINLY_CLEAR,
                    WeatherCondition.PARTLY_CLOUDY,
                    WeatherCondition.OVERCAST,
                    WeatherCondition.SNOW_GRAINS,
                    WeatherCondition.SLIGHT_SNOW_FALL,
                    WeatherCondition.MODERATE_SNOW_FALL,
                    WeatherCondition.HEAVY_INTENSITY_SNOW_FALL
                )
            ),

            // Lower body items
            ClothingItem(
                clothingType = ClothingType.SHORTS,
                description = "Casual shorts",
                suitableTemperatureRange = TemperatureRange(22.0, 40.0),
                suitableConditions = setOf(
                    WeatherCondition.CLEAR_SKY,
                    WeatherCondition.MAINLY_CLEAR,
                    WeatherCondition.PARTLY_CLOUDY
                )
            ),
            ClothingItem(
                clothingType = ClothingType.JEANS,
                description = "Regular denim jeans",
                suitableTemperatureRange = TemperatureRange(10.0, 25.0),
                suitableConditions = setOf(
                    WeatherCondition.CLEAR_SKY,
                    WeatherCondition.MAINLY_CLEAR,
                    WeatherCondition.PARTLY_CLOUDY,
                    WeatherCondition.OVERCAST,
                    WeatherCondition.DRIZZLE,
                    WeatherCondition.SLIGHT_RAIN
                )
            ),
            ClothingItem(
                clothingType = ClothingType.SWEATPANTS,
                description = "Warm sweatpants",
                suitableTemperatureRange = TemperatureRange(-10.0, 15.0),
                suitableConditions = setOf(
                    WeatherCondition.OVERCAST,
                    WeatherCondition.FOG,
                    WeatherCondition.SLIGHT_SNOW_FALL,
                    WeatherCondition.MODERATE_SNOW_FALL
                )
            ),

            // Accessories
            ClothingItem(
                clothingType = ClothingType.UMBRELLA,
                description = "Foldable umbrella",
                suitableTemperatureRange = TemperatureRange(-5.0, 30.0),
                suitableConditions = setOf(
                    WeatherCondition.DRIZZLE,
                    WeatherCondition.SLIGHT_RAIN,
                    WeatherCondition.MODERATE_RAIN,
                    WeatherCondition.SLIGHT_RAIN_SHOWERS,
                    WeatherCondition.MODERATE_RAIN_SHOWERS
                )
            ),
            ClothingItem(
                clothingType = ClothingType.SUNGLASSES,
                description = "UV Protection sunglasses",
                suitableTemperatureRange = TemperatureRange(15.0, 40.0),
                suitableConditions = setOf(
                    WeatherCondition.CLEAR_SKY,
                    WeatherCondition.MAINLY_CLEAR
                )
            ),
            ClothingItem(
                clothingType = ClothingType.GLOVES,
                description = "Insulated gloves",
                suitableTemperatureRange = TemperatureRange(-20.0, 5.0),
                suitableConditions = setOf(
                    WeatherCondition.CLEAR_SKY,
                    WeatherCondition.MAINLY_CLEAR,
                    WeatherCondition.PARTLY_CLOUDY,
                    WeatherCondition.OVERCAST,
                    WeatherCondition.SNOW_GRAINS,
                    WeatherCondition.SLIGHT_SNOW_FALL,
                    WeatherCondition.MODERATE_SNOW_FALL
                )
            ),
            ClothingItem(
                clothingType = ClothingType.SCARF,
                description = "Warm scarf",
                suitableTemperatureRange = TemperatureRange(-20.0, 10.0),
                suitableConditions = setOf(
                    WeatherCondition.CLEAR_SKY,
                    WeatherCondition.MAINLY_CLEAR,
                    WeatherCondition.PARTLY_CLOUDY,
                    WeatherCondition.OVERCAST,
                    WeatherCondition.SNOW_GRAINS,
                    WeatherCondition.SLIGHT_SNOW_FALL
                )
            )
        )
    }
}