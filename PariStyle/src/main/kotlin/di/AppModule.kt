package di

import domain.repository.WeatherRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.data.repository.ClothingRepositoryImpl
import org.example.data.repository.WeatherRepositoryImpl
import org.example.domain.repository.ClothingRepository
import org.example.domain.usecase.GetWeatherUseCase
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            /*      install(Logging) {
                      level = LogLevel.All
                      logger = ColoredLogger
                  }*/
        }
    }

    // IO
    single { org.example.presentation.io.ConsolePrinter() as org.example.presentation.io.Printer }
    single { org.example.presentation.io.ConsoleReader() as org.example.presentation.io.InputReader }

    // Repositories
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<ClothingRepository> { ClothingRepositoryImpl() }

    // Use Cases
    single { GetWeatherUseCase(get()) }
    single { org.example.domain.usecase.GetClothingRecommendationUseCase(get(), get()) }

    // Presenters
    single { org.example.presentation.presenter.WeatherRecommendationPresenter(get()) }

    // Views
    single { org.example.presentation.view.WeatherRecommendationCLI(get(), get(), get()) }
}