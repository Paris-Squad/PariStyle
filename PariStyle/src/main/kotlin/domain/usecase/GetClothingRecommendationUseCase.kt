package org.example.domain.usecase

import domain.model.entity.ClothingItem
import org.example.domain.model.entity.Location

class GetClothingRecommendationUseCase(private val getWeatherUseCase: GetWeatherUseCase) {
    suspend fun getClothingRecommendation(location: Location?=null): ClothingItem {
        TODO("Not yet implemented")
    }
}