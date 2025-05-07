package org.example.domain.usecase

import domain.model.entity.ClothingItem
import org.example.domain.model.entity.ClothingType
import org.example.domain.model.entity.Location
import org.example.domain.model.entity.TemperatureRange
import org.example.domain.model.entity.WeatherCondition

class GetClothingRecommendationUseCase(private val getWeatherUseCase: GetWeatherUseCase) {

    suspend fun getClothingRecommendationForCurrentWeather(): ClothingItem {
        val weatherCondition  = getWeatherUseCase.getWeather().weatherCondition
        val weatherTemperature = getWeatherUseCase.getWeather().temperature
        return pickSuitableClothes(weatherCondition, weatherTemperature)
    }

    suspend fun getClothingRecommendationForCurrentWeatherInSpecificLocation(location: Location): ClothingItem{
        val weatherCondition = getWeatherUseCase.getLocationWeather(location).weatherCondition
        val weatherTemperature = getWeatherUseCase.getLocationWeather(location).temperature
        return pickSuitableClothes(weatherCondition,weatherTemperature)
    }

    private fun pickSuitableClothes(weatherCondition: WeatherCondition, temperature : Double): ClothingItem{
       val suitableClothes =  clothingItemOptions.filter { clothingItem ->
            clothingItem.suitableConditions.contains(weatherCondition) &&
                    isWithinTemperatureRange(temperature,clothingItem)
        }
        return suitableClothes.firstOrNull() ?: emptyClothingItem

    }

    private fun isWithinTemperatureRange(temperature : Double, clothingItem: ClothingItem) = temperature in
                clothingItem.suitableTemperatureRange.min..clothingItem.suitableTemperatureRange.max

    companion object{
        private val clothingItemOptions =  listOf(
            ClothingItem(
                clothingType = ClothingType.T_SHIRT,
                description = "Light short-sleeve shirt",
                suitableTemperatureRange = TemperatureRange(20.0, 40.0),
                suitableConditions = setOf(
                    WeatherCondition.CLEAR_SKY,
                    WeatherCondition.MAINLY_CLEAR,
                    WeatherCondition.PARTLY_CLOUDY
                )
            ),
            ClothingItem(
                clothingType = ClothingType.SHIRT,
                description = "Regular long-sleeve shirt",
                suitableTemperatureRange = TemperatureRange(15.0, 25.0),
                suitableConditions = setOf(
                    WeatherCondition.CLEAR_SKY,
                    WeatherCondition.MAINLY_CLEAR,
                    WeatherCondition.PARTLY_CLOUDY,
                    WeatherCondition.OVERCAST
                )
            ),
            ClothingItem(
                clothingType = ClothingType.RAINCOAT,
                description = "Waterproof raincoat",
                suitableTemperatureRange = TemperatureRange(-10.0, 25.0),
                suitableConditions = setOf(
                    WeatherCondition.DRIZZLE,
                    WeatherCondition.LIGHT_FREEZING_DRIZZLE,
                    WeatherCondition.SLIGHT_RAIN,
                    WeatherCondition.MODERATE_RAIN,
                    WeatherCondition.HEAVY_INTENSITY_RAIN,
                    WeatherCondition.LIGHT_FREEZING_RAIN,
                    WeatherCondition.HEAVY_INTENSITY_FREEZING_RAIN,
                    WeatherCondition.SLIGHT_RAIN_SHOWERS,
                    WeatherCondition.MODERATE_RAIN_SHOWERS,
                    WeatherCondition.VIOLENT_RAIN_SHOWERS
                )
            ),
            ClothingItem(
                clothingType = ClothingType.UMBRELLA,
                description = "Foldable umbrella",
                suitableTemperatureRange = TemperatureRange(-5.0, 30.0),
                suitableConditions = setOf(
                    WeatherCondition.DRIZZLE,
                    WeatherCondition.SLIGHT_RAIN,
                    WeatherCondition.MODERATE_RAIN,
                    WeatherCondition.SLIGHT_RAIN_SHOWERS,
                    WeatherCondition.MODERATE_RAIN_SHOWERS
                )
            )
        )

       private val emptyClothingItem = ClothingItem(
            clothingType = ClothingType.UNKNOWN,
            description = "no description",
            suitableTemperatureRange = TemperatureRange(-70.0,70.0),
            suitableConditions = setOf(WeatherCondition.UNKNOWN)
        )
    }
}
