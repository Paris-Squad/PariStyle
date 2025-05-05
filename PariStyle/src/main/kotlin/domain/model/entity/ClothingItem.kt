package domain.model.entity

import org.example.domain.model.entity.ClothingType
import org.example.domain.model.entity.TemperatureRange
import org.example.domain.model.entity.WeatherCondition

data class ClothingItem(
    val type: ClothingType,
    val description: String,
    val suitableTemperatureRange: TemperatureRange,
    val suitableConditions: Set<WeatherCondition>
)