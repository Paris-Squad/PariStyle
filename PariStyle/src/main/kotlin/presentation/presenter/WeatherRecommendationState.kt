package org.example.presentation.presenter

import org.example.domain.model.entity.cloth.ClothingRecommendation

sealed interface WeatherRecommendationState{
    data object Initial : WeatherRecommendationState
    data object Loading : WeatherRecommendationState
    data class Success(val recommendation: ClothingRecommendation) : WeatherRecommendationState
    data class Error(val exception: Throwable) : WeatherRecommendationState
}
