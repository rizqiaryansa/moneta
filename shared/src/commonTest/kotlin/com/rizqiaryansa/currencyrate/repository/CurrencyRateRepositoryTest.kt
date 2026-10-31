package com.rizqiaryansa.currencyrate.repository

import com.rizqiaryansa.currencyrate.data.source.local.CurrencyCountryData
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateData
import com.rizqiaryansa.currencyrate.data.source.toCurrencyCountryEntities
import com.rizqiaryansa.currencyrate.data.source.toCurrencyRateEntities
import com.rizqiaryansa.currencyrate.data.source.toDomainCurrencies
import com.rizqiaryansa.currencyrate.data.source.toDomainExchangeRates
import com.rizqiaryansa.currencyrate.utils.getCurrencyCountryEntities
import com.rizqiaryansa.currencyrate.utils.getCurrencyMap
import com.rizqiaryansa.currencyrate.utils.getCurrencyRateEntities
import com.rizqiaryansa.currencyrate.utils.getCurrencyRateMap
import kotlinx.coroutines.test.runTest
import kotlinx.io.IOException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.time.Clock

class CurrencyRateRepositoryTest : CurrencyRateRepositoryTestFixture() {

    @Test
    fun `given cached currency data is usable when getCurrencies then returns cached currencies without fetching remote`() {
        runTest(rule.dispatcher.testDispatcher) {
            val entities = getCurrencyCountryEntities()
            val cachedData = CurrencyCountryData(
                id = CurrencyCountryData.CACHE_ROW_ID,
                timeStamp = Clock.System.now().toEpochMilliseconds(),
                currencyCountryList = entities
            )

            onGetCachedCurrencyCountryData_thenReturn(cachedData)

            val result = repository.getCurrencies()

            assertEquals(cachedData.toDomainCurrencies(), result)
            verifyGetCachedCurrencyCountryData()
            verifyGetRemoteCurrencyList(exactly = 0)
        }
    }

    @Test
    fun `given cached currency data is expired when getCurrencies then fetches remote and caches fresh data and returns domain currencies`() {
        runTest(rule.dispatcher.testDispatcher) {
            val currencyMap = getCurrencyMap()
            val currentTime = 123456789L
            onGetCurrentTimeInMillis_thenReturn(currentTime)

            val expiredCache = CurrencyCountryData(
                id = CurrencyCountryData.CACHE_ROW_ID,
                timeStamp = 0L,
                currencyCountryList = getCurrencyCountryEntities()
            )
            onGetCachedCurrencyCountryData_thenReturn(expiredCache)
            onGetRemoteCurrencyList_thenReturn(currencyMap)
            onUpsertCurrencyRates_thenReturnUnit()

            val result = repository.getCurrencies()

            val expectedEntities = currencyMap.toCurrencyCountryEntities()
            val expectedFreshData = CurrencyCountryData(
                timeStamp = currentTime,
                currencyCountryList = expectedEntities
            )
            assertEquals(expectedFreshData.toDomainCurrencies(), result)
            verifyGetRemoteCurrencyList()
            verifyUpsertCurrencyList(expectedFreshData)
        }
    }

    @Test
    fun `given cache is expired and remote fails but cache has currencies when getCurrencies then falls back to cached currencies`() {
        runTest(rule.dispatcher.testDispatcher) {
            val expiredCache = CurrencyCountryData(
                id = CurrencyCountryData.CACHE_ROW_ID,
                timeStamp = 0L,
                currencyCountryList = getCurrencyCountryEntities()
            )
            onGetCachedCurrencyCountryData_thenReturn(expiredCache)
            onGetRemoteCurrencyList_thenReturnError(IOException("Connection failed"))

            val result = repository.getCurrencies()

            assertEquals(expiredCache.toDomainCurrencies(), result)
            verifyGetRemoteCurrencyList()
        }
    }

    @Test
    fun `given cache is null and remote fails when getCurrencies then throws exception`() {
        runTest(rule.dispatcher.testDispatcher) {
            onGetCachedCurrencyCountryData_thenReturn(null)
            onGetRemoteCurrencyList_thenReturnError(IOException("Network error"))

            assertFailsWith<IOException>() {
                repository.getCurrencies()
            }
        }
    }

    @Test
    fun `given cached exchange rates data is usable when getExchangeRates then returns cached exchange rates without fetching remote`() {
        runTest(rule.dispatcher.testDispatcher) {
            val entities = getCurrencyRateEntities()
            val cachedData = CurrencyRateData(
                id = CurrencyRateData.CACHE_ROW_ID,
                timeStamp = Clock.System.now().toEpochMilliseconds(),
                currencyRateEntityList = entities
            )
            onGetCachedCurrencyRateData_thenReturn(cachedData)
            val result = repository.getExchangeRates()
            assertEquals(cachedData.toDomainExchangeRates(), result)
            verifyGetCachedCurrencyRateData()
            verifyGetRemoteCurrencyRateList(exactly = 0)
        }
    }

    @Test
    fun `given cached exchange rates data is expired when getExchangeRates then fetches remote and caches fresh rates and returns domain rates`() {
        runTest(rule.dispatcher.testDispatcher) {
            val ratesMap = getCurrencyRateMap()
            val fixedTime = 123456789L
            onGetCurrentTimeInMillis_thenReturn(fixedTime)
            val expiredCache = CurrencyRateData(
                id = CurrencyRateData.CACHE_ROW_ID,
                timeStamp = 0L,
                currencyRateEntityList = getCurrencyRateEntities()
            )

            onGetCachedCurrencyRateData_thenReturn(expiredCache)
            onGetRemoteCurrencyRateList_thenReturn(ratesMap)
            onUpsertCurrencyRates_thenReturnUnit()

            val result = repository.getExchangeRates()
            val expectedEntities = ratesMap.toCurrencyRateEntities()
            val expectedFreshData = CurrencyRateData(
                timeStamp = fixedTime,
                currencyRateEntityList = expectedEntities
            )

            assertEquals(expectedFreshData.toDomainExchangeRates(), result)
            verifyGetRemoteCurrencyRateList()
            verifyUpsertCurrencyRates(expectedFreshData)
        }
    }

    @Test
    fun `given cache is expired and remote fails but cache has exchange rates when getExchangeRates then falls back to cached rates`() {
        runTest(rule.dispatcher.testDispatcher) {
            val expiredCache = CurrencyRateData(
                id = CurrencyRateData.CACHE_ROW_ID,
                timeStamp = 0L,
                currencyRateEntityList = getCurrencyRateEntities()
            )
            onGetCachedCurrencyRateData_thenReturn(expiredCache)
            onGetRemoteCurrencyRateList_thenReturnError(IOException("Connection failed"))

            val result = repository.getExchangeRates()
            assertEquals(expiredCache.toDomainExchangeRates(), result)
            verifyGetRemoteCurrencyRateList()
        }
    }

    @Test
    fun `given cache is null and remote fails when getExchangeRates then throws exception`() =
        runTest(rule.dispatcher.testDispatcher) {
            onGetCachedCurrencyRateData_thenReturn(null)
            onGetRemoteCurrencyRateList_thenReturnError(IOException("Network error"))

            assertFailsWith<IOException> {
                repository.getExchangeRates()
            }
        }
}