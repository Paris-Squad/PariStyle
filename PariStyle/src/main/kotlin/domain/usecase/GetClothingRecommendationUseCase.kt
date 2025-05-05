package org.example.domain.usecase

import domain.model.entity.ClothingItem

class GetClothingRecommendationUseCase(private val getWeatherUseCase: GetWeatherUseCase) {
    suspend fun getClothingRecommendation(): ClothingItem {
        TODO("Not yet implemented")
    }
}