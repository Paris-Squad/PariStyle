package org.example.domain.model.entity.weather

enum class WeatherCondition(val displayName: String) {
    CLEAR_SKY("Clear sky"),
    MAINLY_CLEAR("Mainly clear"),
    PARTLY_CLOUDY("Partly cloudy"),
    OVERCAST("Overcast"),
    FOG("Foggy"),
    DRIZZLE("Drizzle"),
    LIGHT_FREEZING_DRIZZLE("Light freezing drizzle"),
    SLIGHT_RAIN("Slight rain"),
    MODERATE_RAIN("Moderate rain"),
    HEAVY_INTENSITY_RAIN("Heavy rain"),
    LIGHT_FREEZING_RAIN("Light freezing rain"),
    HEAVY_INTENSITY_FREEZING_RAIN("Heavy freezing rain"),
    SLIGHT_SNOW_FALL("Light snow"),
    MODERATE_SNOW_FALL("Moderate snow"),
    HEAVY_INTENSITY_SNOW_FALL("Heavy snow"),
    SNOW_GRAINS("Snow grains"),
    SLIGHT_RAIN_SHOWERS("Light rain showers"),
    MODERATE_RAIN_SHOWERS("Moderate rain showers"),
    VIOLENT_RAIN_SHOWERS("Violent rain showers"),
    SLIGHT_SNOW_SHOWERS("Light snow showers"),
    HEAVY_SNOW_SHOWERS("Heavy snow showers"),
    SLIGHT_THUNDERSTORM("Slight thunderstorm"),
    SLIGHT_THUNDERSTORM_WITH_HAIL("Thunderstorm with hail"),
    UNKNOWN("Unknown weather condition");
}