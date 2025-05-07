package org.example.presentation.presenter

import domain.model.entity.ClothingItem

sealed interface WeatherRecommendationState{
    data object Initial : WeatherRecommendationState
    data object Loading : WeatherRecommendationState
    data class Success(val recommendation: ClothingItem) : WeatherRecommendationState
    data class Error(val exception: Throwable) : WeatherRecommendationState
}
