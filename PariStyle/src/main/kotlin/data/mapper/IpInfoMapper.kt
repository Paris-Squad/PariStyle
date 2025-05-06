package org.example.data.mapper

import org.example.data.model.IpInfoDTO
import org.example.domain.model.entity.Location

fun IpInfoDTO.toLocation() =
    Location(latitude = this.lat, longitude = this.lon)
