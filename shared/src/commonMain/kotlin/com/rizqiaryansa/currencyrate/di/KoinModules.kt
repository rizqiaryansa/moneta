package com.rizqiaryansa.currencyrate.di

import com.rizqiaryansa.currencyrate.core.common.dispatcher.CoroutineDispatchers
import com.rizqiaryansa.currencyrate.core.common.dispatcher.CoroutineDispatchersProvider
import com.rizqiaryansa.currencyrate.core.common.time.DefaultTimeProvider
import com.rizqiaryansa.currencyrate.core.common.time.TimeProvider
import com.rizqiaryansa.currencyrate.data.CurrencyRateRepositoryImpl
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateDao
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateDatabase
import com.rizqiaryansa.currencyrate.data.source.local.currencylist.CurrencyListLocalDataSource
import com.rizqiaryansa.currencyrate.data.source.local.currencylist.CurrencyListLocalDataSourceImpl
import com.rizqiaryansa.currencyrate.data.source.local.currencyrate.CurrencyRateLocalDataSource
import com.rizqiaryansa.currencyrate.data.source.local.currencyrate.CurrencyRateLocalDataSourceImpl
import com.rizqiaryansa.currencyrate.data.source.local.getRoomDatabase
import com.rizqiaryansa.currencyrate.data.source.remote.CurrencyRateApiService
import com.rizqiaryansa.currencyrate.data.source.remote.CurrencyRateRemoteDataSource
import com.rizqiaryansa.currencyrate.data.source.remote.CurrencyRateRemoteDataSourceImpl
import com.rizqiaryansa.currencyrate.data.source.remote.KtorCurrencyRateApiService
import com.rizqiaryansa.currencyrate.domain.CurrencyRateRepository
import com.rizqiaryansa.currencyrate.domain.usecase.GetCurrenciesListUseCase
import com.rizqiaryansa.currencyrate.domain.usecase.GetCurrenciesRateUseCase
import com.rizqiaryansa.currencyrate.domain.usecase.SearchCurrenciesUseCase
import com.rizqiaryansa.currencyrate.presentation.viewmodel.CurrencyListViewModel
import com.rizqiaryansa.currencyrate.presentation.viewmodel.CurrencyRateViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val commonModule = module {
    // Core
    singleOf(::CoroutineDispatchersProvider) bind CoroutineDispatchers::class
    singleOf(::DefaultTimeProvider) bind TimeProvider::class

    // Network (Ktor)
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(Logging) {
                level = LogLevel.BODY
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 20_000L
                connectTimeoutMillis = 20_000L
                socketTimeoutMillis = 20_000L
            }
        }
    }

    singleOf(::KtorCurrencyRateApiService) bind CurrencyRateApiService::class
    single { CurrencyRateRemoteDataSourceImpl(apiService = get()) } bind CurrencyRateRemoteDataSource::class

    //Local Persistence (Room KMP)
    single<CurrencyRateDatabase> { getRoomDatabase(get()) }
    single<CurrencyRateDao> { get<CurrencyRateDatabase>().currencyRateDao() }
    singleOf(::CurrencyListLocalDataSourceImpl) bind CurrencyListLocalDataSource::class
    singleOf(::CurrencyRateLocalDataSourceImpl) bind CurrencyRateLocalDataSource::class

    //Repository & Domain UseCases
    singleOf(::CurrencyRateRepositoryImpl) bind CurrencyRateRepository::class
    factoryOf(::GetCurrenciesListUseCase)
    factoryOf(::GetCurrenciesRateUseCase)
    factoryOf(::SearchCurrenciesUseCase)

    //ViewModel (using Koin inject() to provide kotlin.Lazy<UseCase>)
    viewModel {
        CurrencyRateViewModel(
            getCurrenciesRateUseCase = inject(),
            savedStateHandle = get()
        )
    }

    viewModel {
        CurrencyListViewModel(
            getCurrenciesListUseCase = inject(),
            searchCurrenciesUseCase = inject()
        )
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(platformModule, commonModule)
    }
}