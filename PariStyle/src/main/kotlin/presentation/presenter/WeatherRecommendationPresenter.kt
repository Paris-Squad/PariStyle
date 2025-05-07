package org.example.presentation.presenter

import org.example.domain.model.entity.cloth.ClothingRecommendation
import org.example.domain.model.entity.weather.Location
import org.example.domain.usecase.GetClothingRecommendationUseCase

class WeatherRecommendationPresenter(
    private val getClothingRecommendationUseCase: GetClothingRecommendationUseCase
) : BasePresenter<ClothingRecommendation, WeatherRecommendationState>(WeatherRecommendationState.Initial) {

    suspend fun getCurrentLocationRecommendation() {
        emitLoading()
        executeWithErrorHandling(block = {
            getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeather()
        }, onError = { exception ->
            _state.value = WeatherRecommendationState.Error(exception = exception)
        }, onSuccess = { recommendation ->
            _state.value = WeatherRecommendationState.Success(recommendation = recommendation)
        })
    }

    suspend fun getSpecificLocationRecommendation(latitude: Double, longitude: Double) {
        emitLoading()
        executeWithErrorHandling(block = {
            getClothingRecommendationUseCase.getClothingRecommendationForCurrentWeatherInSpecificLocation(
                Location(
                    latitude,
                    longitude
                )
            )
        }, onError = { exception ->
            _state.value = WeatherRecommendationState.Error(exception = exception)
        }, onSuccess = { recommendation ->
            _state.value = WeatherRecommendationState.Success(recommendation = recommendation)
        })
    }

    fun emitLoading() {
        _state.value = WeatherRecommendationState.Loading
    }
}
