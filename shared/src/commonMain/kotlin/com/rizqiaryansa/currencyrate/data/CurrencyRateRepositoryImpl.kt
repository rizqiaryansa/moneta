package com.rizqiaryansa.currencyrate.data

import com.rizqiaryansa.currencyrate.core.common.dispatcher.CoroutineDispatchers
import com.rizqiaryansa.currencyrate.core.common.time.TimeProvider
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyCountryData
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateData
import com.rizqiaryansa.currencyrate.data.source.local.currencylist.CurrencyListLocalDataSource
import com.rizqiaryansa.currencyrate.data.source.local.currencyrate.CurrencyRateLocalDataSource
import com.rizqiaryansa.currencyrate.data.source.remote.CurrencyRateRemoteDataSource
import com.rizqiaryansa.currencyrate.data.source.toCurrencyCountryEntities
import com.rizqiaryansa.currencyrate.data.source.toCurrencyRateEntities
import com.rizqiaryansa.currencyrate.data.source.toDomainCurrencies
import com.rizqiaryansa.currencyrate.data.source.toDomainExchangeRates
import com.rizqiaryansa.currencyrate.domain.CurrencyRateRepository
import com.rizqiaryansa.currencyrate.domain.model.Currency
import com.rizqiaryansa.currencyrate.domain.model.ExchangeRate
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class CurrencyRateRepositoryImpl(
    private val remoteDataSource: CurrencyRateRemoteDataSource,
    private val currencyListLocalDataSource: CurrencyListLocalDataSource,
    private val rateLocalDataSource: CurrencyRateLocalDataSource,
    private val dispatcher: CoroutineDispatchers,
    private val timeProvider: TimeProvider
) : CurrencyRateRepository {

    // Make mutex as different instance to prevent locking each other
    private val currencyCountryMutex = Mutex()
    private val currencyRateMutex = Mutex()

    override suspend fun getCurrencies(): List<Currency> {
        return withContext(dispatcher.io) {
            currencyCountryMutex.withLock {
                val cachedData = currencyListLocalDataSource.getCurrencyCountryData()

                if (cachedData?.isUsable() == true) {
                    return@withLock cachedData.toDomainCurrencies()
                }

                try {
                    val entities = remoteDataSource
                        .getCurrencyList()
                        .toCurrencyCountryEntities()

                    val freshData = CurrencyCountryData(
                        timeStamp = timeProvider.getCurrentTimeInMillis(),
                        currencyCountryList = entities
                    )

                    currencyListLocalDataSource.upsertCurrencyList(freshData)
                    freshData.toDomainCurrencies()
                } catch (cancellation: CancellationException) {
                    throw cancellation
                } catch (e: Exception) {
                    if (cachedData?.currencyCountryList?.isNotEmpty() == true) {
                        cachedData.toDomainCurrencies()
                    } else {
                        throw e
                    }
                }
            }
        }
    }

    override suspend fun getExchangeRates(): List<ExchangeRate> {
        return withContext(dispatcher.io) {
            currencyRateMutex.withLock {
                val cachedData = rateLocalDataSource.getCurrencyRateData()

                if (cachedData?.isUsable() == true) {
                    return@withLock cachedData.toDomainExchangeRates()
                }

                try {
                    val entities = remoteDataSource
                        .getCurrencyRateList()
                        .toCurrencyRateEntities()

                    val freshData = CurrencyRateData(
                        timeStamp = timeProvider.getCurrentTimeInMillis(),
                        currencyRateEntityList = entities
                    )

                    rateLocalDataSource.upsertCurrencyRates(freshData)
                    freshData.toDomainExchangeRates()
                } catch (cancellation: CancellationException) {
                    throw cancellation
                } catch (e: Exception) {
                    if (cachedData?.currencyRateEntityList?.isNotEmpty() == true) {
                        cachedData.toDomainExchangeRates()
                    } else {
                        throw e
                    }
                }
            }
        }
    }
}