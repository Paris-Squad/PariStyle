package org.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IpInfoDTO(
    @SerialName("status") val status: String? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("regionName") val regionName: String? = null,
    @SerialName("city") val city: String? = null,
    @SerialName("lat") val latitude: Double? = null,
    @SerialName("lon") val longitude: Double? = null,
    @SerialName("timezone") val timezone: String? = null,
)