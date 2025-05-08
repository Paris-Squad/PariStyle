package org.example.domain.model.entity.cloth

import domain.model.entity.ClothingItem

data class ClothingRecommendation(
    val topItem: ClothingItem,
    val bottomItem: ClothingItem,
    val accessoryItem: ClothingItem?
) {
    fun formatRecommendation() = buildString {
        appendLine("today's weather will be like ${topItem.suitableConditions.map { it.displayName }}")
        appendLine("Recommended Outfit:")
        appendLine("🔹 Top: ${topItem.description}")
        appendLine("🔹 Bottom: ${bottomItem.description}")
        if (accessoryItem != null) {
            appendLine("🔹 Accessory: ${accessoryItem.description}")
        }
    }
}