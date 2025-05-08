package domain.model.entity

import org.example.domain.model.entity.cloth.enums.ClothingType
import org.example.domain.model.entity.weather.TemperatureRange
import org.example.domain.model.entity.weather.WeatherCondition

data class ClothingItem(
    val clothingType: ClothingType,
    val description: String,
    val suitableTemperatureRange: TemperatureRange,
    val suitableConditions: Set<WeatherCondition>
)