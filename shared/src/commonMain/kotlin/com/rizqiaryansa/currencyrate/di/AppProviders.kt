package com.rizqiaryansa.currencyrate.di

import androidx.room.RoomDatabase
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateDao
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateDatabase
import com.rizqiaryansa.currencyrate.data.source.local.getRoomDatabase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

const val DEFAULT_TIMEOUT_MILLIS = 20_000L

@ContributesTo(AppScope::class)
interface AppProviders {

    @Provides
    @SingleIn(AppScope::class)
    fun provideHttpClient(): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging) {
            level = LogLevel.BODY
        }
        install(HttpTimeout) {
            requestTimeoutMillis = DEFAULT_TIMEOUT_MILLIS
            connectTimeoutMillis = DEFAULT_TIMEOUT_MILLIS
            socketTimeoutMillis = DEFAULT_TIMEOUT_MILLIS
        }
    }

    @Provides
    @SingleIn(AppScope::class)
    fun provideDatabase(
        builder: RoomDatabase.Builder<CurrencyRateDatabase>
    ): CurrencyRateDatabase = getRoomDatabase(builder)

    @Provides
    @SingleIn(AppScope::class)
    fun provideCurrencyRateDao(
        database: CurrencyRateDatabase
    ): CurrencyRateDao = database.currencyRateDao()
}