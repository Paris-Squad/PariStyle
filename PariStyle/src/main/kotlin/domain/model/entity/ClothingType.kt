package org.example.domain.model.entity

enum class ClothingCategory {
    UPPER_BODY,
    LOWER_BODY,
    ACCESSORY,
    UNKNOWN
}

enum class ClothingType(val displayName: String, val category: ClothingCategory) {
    T_SHIRT("T-shirt", ClothingCategory.UPPER_BODY),
    SHIRT("Shirt", ClothingCategory.UPPER_BODY),
    SWEATER("Sweater", ClothingCategory.UPPER_BODY),
    HOODIE("Hoodie", ClothingCategory.UPPER_BODY),
    LIGHT_JACKET("Light jacket", ClothingCategory.UPPER_BODY),
    HEAVY_JACKET("Heavy jacket", ClothingCategory.UPPER_BODY),
    COAT("Coat", ClothingCategory.UPPER_BODY),
    RAINCOAT("Raincoat", ClothingCategory.UPPER_BODY),

    SHORTS("Shorts", ClothingCategory.LOWER_BODY),
    JEANS("Jeans", ClothingCategory.LOWER_BODY),
    PANTS("Pants", ClothingCategory.LOWER_BODY),
    SWEATPANTS("Sweatpants", ClothingCategory.LOWER_BODY),
    SKIRT("Skirt", ClothingCategory.LOWER_BODY),

    HAT("Hat", ClothingCategory.ACCESSORY),
    CAP("Cap", ClothingCategory.ACCESSORY),
    SCARF("Scarf", ClothingCategory.ACCESSORY),
    GLOVES("Gloves", ClothingCategory.ACCESSORY),
    SUNGLASSES("Sunglasses", ClothingCategory.ACCESSORY),
    UMBRELLA("Umbrella", ClothingCategory.ACCESSORY),

    UNKNOWN("Unknown", ClothingCategory.UNKNOWN);
}