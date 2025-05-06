package domain.usecase

import com.google.common.truth.Truth.assertThat
import domain.model.entity.Weather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.domain.model.entity.WeatherCondition
import org.example.domain.usecase.GetClothingRecommendationUseCase
import org.example.domain.usecase.GetWeatherUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetClothingRecommendationUseCaseTest {
    private val getWeatherUseCase: GetWeatherUseCase = mockk()
    private lateinit var getClothingRecommendationUseCase: GetClothingRecommendationUseCase

    @BeforeEach
    fun setUp() {
        getClothingRecommendationUseCase = GetClothingRecommendationUseCase(getWeatherUseCase)
    }

    @Test
    fun `should return correct clothing recommendation based on weather`() = runTest {
        try {

        } catch (e: Throwable) {
            assertThat(e).isInstanceOf(NotImplementedError::class.java)
        }
    }
}