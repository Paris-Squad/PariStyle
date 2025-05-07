package org.example.domain.repository

import domain.model.entity.ClothingItem
import org.example.domain.model.entity.cloth.enums.ClothingCategory
import org.example.domain.model.entity.weather.WeatherCondition

interface ClothingRepository {
    fun getClothingItemsByCategoryAndConditions(
        category: ClothingCategory,
        weatherConditions: Set<WeatherCondition>,
        temperature: Double
    ): List<ClothingItem>
}