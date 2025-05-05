package di

import org.example.data.repository.WeatherRepositoryImpl
import domain.repository.WeatherRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
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
        }
    }

    // Repositories
    single<WeatherRepository> { WeatherRepositoryImpl() }

    // Use Cases
    single { GetWeatherUseCase(get()) }
    single { GetWeatherUseCase(get()) }
}