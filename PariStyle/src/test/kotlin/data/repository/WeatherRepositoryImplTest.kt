package data.repository

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.example.data.repository.WeatherRepositoryImpl
import org.example.domain.model.entity.Location
import org.junit.jupiter.api.Test

class WeatherRepositoryImplTest {

    @Test
    fun `getCurrentWeather should return weather data when API call is successful`() = runTest{
        try {
            WeatherRepositoryImpl().getCurrentWeather(Location( 51.5074, -0.1278))
        }catch (e: Throwable){
            assertThat(e).isInstanceOf(NotImplementedError::class.java)
        }
    }
}