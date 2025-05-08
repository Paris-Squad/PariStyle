package data.mapper

import com.google.common.truth.Truth.assertThat
import org.example.data.mapper.toLocation
import org.example.data.model.IpInfoDTO
import org.example.domain.model.entity.weather.Location
import org.junit.jupiter.api.Test

class IpInfoMapperTest {

    @Test
    fun `toLocation should map latitude and longitude correctly`() {
        // Given
        val ipInfoDTO = IpInfoDTO(
            status = "success",
            country = "United States",
            regionName = "California",
            city = "San Francisco",
            latitude = 37.7749,
            longitude = -122.4194,
            timezone = "America/Los_Angeles"
        )

        val result = ipInfoDTO.toLocation()

        assertThat(result).isEqualTo(Location(latitude = 37.7749, longitude = -122.4194))
    }

    @Test
    fun `toLocation should default both values to zero when latitude and longitude are null`() {
        val ipInfoDTO = IpInfoDTO(
            status = "success",
            country = "United States",
            regionName = "California",
            city = "San Francisco",
            latitude = null,
            longitude = null,
            timezone = "America/Los_Angeles"
        )

        val result = ipInfoDTO.toLocation()

        assertThat(result).isEqualTo(Location(latitude = 0.0, longitude = 0.0))
    }

    @Test
    fun `toLocation should return latitude with zero value when latitude null`() {
        val ipInfoDTO = IpInfoDTO(
            status = "success",
            country = "United States",
            regionName = "California",
            city = "San Francisco",
            latitude = null,
            longitude = -122.4194,
            timezone = "America/Los_Angeles"
        )

        val result = ipInfoDTO.toLocation()

        assertThat(result).isEqualTo(Location(latitude = 0.0, longitude = -122.4194))
    }

    @Test
    fun `toLocation should return longitude with zero value when longitude null`() {
        val ipInfoDTO = IpInfoDTO(
            status = "success",
            country = "United States",
            regionName = "California",
            city = "San Francisco",
            latitude = 37.7749,
            longitude = null,
            timezone = "America/Los_Angeles"
        )

        val result = ipInfoDTO.toLocation()

        assertThat(result).isEqualTo(Location(latitude = 37.7749, longitude = 0.0))
    }
}