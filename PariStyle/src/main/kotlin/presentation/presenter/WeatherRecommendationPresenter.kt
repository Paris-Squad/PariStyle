package org.example.presentation.presenter

import domain.model.entity.ClothingItem
import org.example.domain.model.entity.Location
import org.example.domain.usecase.GetClothingRecommendationUseCase

class WeatherRecommendationPresenter(
    private val getClothingRecommendationUseCase: GetClothingRecommendationUseCase
) {
    suspend fun getCurrentLocationRecommendation(): ClothingItem {
        return getClothingRecommendationUseCase.getClothingRecommendation()
    }

    suspend fun getSpecificLocationRecommendation(latitude: Double, longitude: Double): ClothingItem {
        return getClothingRecommendationUseCase.getClothingRecommendation(
            Location(latitude, longitude)
        )
    }
}
