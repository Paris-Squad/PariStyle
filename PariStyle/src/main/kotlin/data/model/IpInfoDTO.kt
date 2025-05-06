package org.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class IpInfoDTO(
    val status: String,
    val country: String,
    val regionName: String,
    val city: String,
    val lat: Double,
    val lon: Double,
    val timezone: String
)