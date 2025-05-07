package org.example.domain.usecase

import org.example.domain.model.entity.cloth.ClothingRecommendation
import org.example.domain.model.entity.cloth.enums.ClothingCategory
import org.example.domain.model.entity.weather.Location
import org.example.domain.model.entity.weather.WeatherCondition
import org.example.domain.model.exception.PariStyleException
import org.example.domain.repository.ClothingRepository

class GetClothingRecommendationUseCase(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val clothingRepository: ClothingRepository
) {

    suspend fun getClothingRecommendationForCurrentWeather(): ClothingRecommendation {
        val weather = getWeatherUseCase.getWeather()
        return getClothingRecommendation(weather.weatherCondition, weather.temperature)
    }

    suspend fun getClothingRecommendationForCurrentWeatherInSpecificLocation(location: Location): ClothingRecommendation {
        val weather = getWeatherUseCase.getLocationWeather(location)
        return getClothingRecommendation(weather.weatherCondition, weather.temperature)
    }

    private fun getClothingRecommendation(
        weatherCondition: WeatherCondition,
        temperature: Double
    ): ClothingRecommendation {
        val topItems = clothingRepository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.UPPER_BODY,
            setOf(weatherCondition),
            temperature
        )

        val bottomItems = clothingRepository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.LOWER_BODY,
            setOf(weatherCondition),
            temperature
        )

        val accessoryItems = clothingRepository.getClothingItemsByCategoryAndConditions(
            ClothingCategory.ACCESSORY,
            setOf(weatherCondition),
            temperature
        )

        val topItem = topItems.randomOrNull() ?: throw PariStyleException.NotFoundException("No top items found")
        val bottomItem = bottomItems.randomOrNull() ?: throw PariStyleException.NotFoundException("No bottom items found")
        val accessoryItem = if (accessoryItems.isNotEmpty()) accessoryItems.random() else null

        return ClothingRecommendation(
            topItem = topItem,
            bottomItem = bottomItem,
            accessoryItem = accessoryItem
        )
    }
}