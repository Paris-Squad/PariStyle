package org.example.domain.model.entity.cloth.enums

enum class ClothingType(val displayName: String, val category: ClothingCategory, val weatherType: ClothWeatherType) {
    T_SHIRT("T-shirt", ClothingCategory.UPPER_BODY, ClothWeatherType.LIGHT),
    SHIRT("Shirt", ClothingCategory.UPPER_BODY, ClothWeatherType.LIGHT),
    SWEATER("Sweater", ClothingCategory.UPPER_BODY, ClothWeatherType.MEDIUM),
    HOODIE("Hoodie", ClothingCategory.UPPER_BODY, ClothWeatherType.MEDIUM),
    LIGHT_JACKET("Light jacket", ClothingCategory.UPPER_BODY, ClothWeatherType.MEDIUM),
    HEAVY_JACKET("Heavy jacket", ClothingCategory.UPPER_BODY, ClothWeatherType.HEAVY),
    COAT("Coat", ClothingCategory.UPPER_BODY, ClothWeatherType.VERY_HEAVY),
    RAINCOAT("Raincoat", ClothingCategory.UPPER_BODY, ClothWeatherType.HEAVY),

    SHORTS("Shorts", ClothingCategory.LOWER_BODY, ClothWeatherType.VERY_LIGHT),
    JEANS("Jeans", ClothingCategory.LOWER_BODY, ClothWeatherType.MEDIUM),
    PANTS("Pants", ClothingCategory.LOWER_BODY, ClothWeatherType.MEDIUM),
    SWEATPANTS("Sweatpants", ClothingCategory.LOWER_BODY, ClothWeatherType.MEDIUM),
    SKIRT("Skirt", ClothingCategory.LOWER_BODY, ClothWeatherType.LIGHT),

    HAT("Hat", ClothingCategory.ACCESSORY, ClothWeatherType.MEDIUM),
    CAP("Cap", ClothingCategory.ACCESSORY, ClothWeatherType.LIGHT),
    SCARF("Scarf", ClothingCategory.ACCESSORY, ClothWeatherType.HEAVY),
    GLOVES("Gloves", ClothingCategory.ACCESSORY, ClothWeatherType.HEAVY),
    SUNGLASSES("Sunglasses", ClothingCategory.ACCESSORY, ClothWeatherType.VERY_LIGHT),
    UMBRELLA("Umbrella", ClothingCategory.ACCESSORY, ClothWeatherType.MEDIUM),

    UNKNOWN("Unknown", ClothingCategory.UNKNOWN, ClothWeatherType.MEDIUM);
}