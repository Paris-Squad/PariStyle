package org.example.data.mapper

import org.example.data.model.IpInfoDTO
import org.example.domain.model.entity.weather.Location

fun IpInfoDTO.toLocation() = Location(latitude = this.latitude?:0.0, longitude = this.longitude?:0.0)
