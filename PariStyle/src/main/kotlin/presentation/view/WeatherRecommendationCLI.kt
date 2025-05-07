package org.example.presentation.view

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.example.presentation.io.InputReader
import org.example.presentation.io.Printer
import org.example.presentation.presenter.WeatherRecommendationPresenter
import org.example.presentation.presenter.WeatherRecommendationState

@OptIn(DelicateCoroutinesApi::class)
class WeatherRecommendationCLI(
    private val printer: Printer,
    private val reader: InputReader,
    private val presenter: WeatherRecommendationPresenter
) {

    fun start() {
        showWelcomeMessage()
        runBlocking {
            async { collectPresenterState() }

            when (getUserChoice()) {
                UserChoice.CURRENT_LOCATION -> handleCurrentLocation()
                UserChoice.SPECIFIC_LOCATION -> handleSpecificLocation()
            }
        }
    }

    private suspend fun collectPresenterState() {
        presenter.state.collect { state ->
            when (state) {
                is WeatherRecommendationState.Error -> printer.displayErr(state.exception)
                is WeatherRecommendationState.Loading -> printer.displayMsg("Loading...")
                is WeatherRecommendationState.Success -> printer.displayLn(state.recommendation)
                else -> {}
            }
        }
    }

    private fun showWelcomeMessage() {
        printer.displayMsg("Weather Clothing Recommender")
        printer.displayMsg("--------------------------")
    }

    private fun getUserChoice(): UserChoice {
        printer.displayLn("\nChoose an option:")
        printer.displayLn(("1. Get recommendation for my current location"))
        printer.displayLn("2. Enter specific location coordinates")
        printer.displayLn("Your choice (1-2): ")

        val userChoice = reader.readInt()
        return when (userChoice) {
            1 -> UserChoice.CURRENT_LOCATION
            2 -> UserChoice.SPECIFIC_LOCATION
            else -> {
                printer.displayLn("Invalid choice, using current location")
                UserChoice.CURRENT_LOCATION
            }
        }
    }

    private suspend fun handleSpecificLocation() {
        var validInput = false

        while (!validInput) {
            printer.displayLn("\nEnter latitude: ")
            val lat = reader.readDouble()
            if (lat == null) {
                printer.displayLn("Invalid latitude")
                continue
            }
            printer.displayLn("Enter longitude: ")
            val lon = reader.readDouble()
            if (lon == null) {
                printer.displayLn("Invalid latitude")
                continue
            }
            validInput = true

            presenter.getSpecificLocationRecommendation(lat, lon)
        }
    }

    private suspend fun handleCurrentLocation() {
        presenter.getCurrentLocationRecommendation()
    }

    private enum class UserChoice {
        CURRENT_LOCATION,
        SPECIFIC_LOCATION
    }
}