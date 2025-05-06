package org.example.presentation.view

import domain.model.entity.ClothingItem
import org.example.presentation.presenter.WeatherRecommendationPresenter

class WeatherRecommendationCLI(
    private val presenter: WeatherRecommendationPresenter
) {
    suspend operator fun invoke() {
        showWelcomeMessage()
        when (getUserChoice()) {
            UserChoice.CURRENT_LOCATION -> handleCurrentLocation()
            UserChoice.SPECIFIC_LOCATION -> handleSpecificLocation()
        }
    }

    private fun showWelcomeMessage() {
        println("Weather Clothing Recommender")
        println("--------------------------")
    }

    private fun getUserChoice(): UserChoice {
        println("\nChoose an option:")
        println("1. Get recommendation for my current location")
        println("2. Enter specific location coordinates")
        print("Your choice (1-2): ")

        return when (readlnOrNull()) {
            "1" -> UserChoice.CURRENT_LOCATION
            "2" -> UserChoice.SPECIFIC_LOCATION
            else -> {
                println("Invalid choice, using current location")
                UserChoice.CURRENT_LOCATION
            }
        }
    }

    private suspend fun handleSpecificLocation() {
        try {
            print("Enter latitude: ")
            val lat = readlnOrNull()?.toDoubleOrNull() ?: throw IllegalArgumentException("Invalid latitude")
            print("Enter longitude: ")
            val lon = readlnOrNull()?.toDoubleOrNull() ?: throw IllegalArgumentException("Invalid longitude")

            val recommendation = presenter.getSpecificLocationRecommendation(lat, lon)
            showRecommendation(recommendation)
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private suspend fun handleCurrentLocation() {
        try {
            val recommendation = presenter.getCurrentLocationRecommendation()
            showRecommendation(recommendation)
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun showRecommendation(recommendation: ClothingItem) {
        println("\n=== Clothing Recommendation ===")
        println(" Clothing Item type: ${recommendation.type}")
    }

    private enum class UserChoice {
        CURRENT_LOCATION,
        SPECIFIC_LOCATION
    }
}