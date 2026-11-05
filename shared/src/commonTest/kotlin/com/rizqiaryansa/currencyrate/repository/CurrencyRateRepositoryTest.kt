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
    fun given_cached_currency_data_is_usable_when_getCurrencies_then_returns_cached_currencies_without_fetching_remote() {
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
    fun given_cached_currency_data_is_expired_when_getCurrencies_then_fetches_remote_and_caches_fresh_data_and_returns_domain_currencies() {
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
    fun given_cache_is_expired_and_remote_fails_but_cache_has_currencies_when_getCurrencies_then_falls_back_to_cached_currencies() {
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
    fun given_cache_is_null_and_remote_fails_when_getCurrencies_then_throws_exception() {
        runTest(rule.dispatcher.testDispatcher) {
            onGetCachedCurrencyCountryData_thenReturn(null)
            onGetRemoteCurrencyList_thenReturnError(IOException("Network error"))

            assertFailsWith<IOException>() {
                repository.getCurrencies()
            }
        }
    }

    @Test
    fun given_cached_exchange_rates_data_is_usable_when_getExchangeRates_then_returns_cached_exchange_rates_without_fetching_remote() {
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
    fun given_cached_exchange_rates_data_is_expired_when_getExchangeRates_then_fetches_remote_and_caches_fresh_rates_and_returns_domain_rates() {
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
    fun given_cache_is_expired_and_remote_fails_but_cache_has_exchange_rates_when_getExchangeRates_then_falls_back_to_cached_rates() {
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
    fun given_cache_is_null_and_remote_fails_when_getExchangeRates_then_throws_exception() =
        runTest(rule.dispatcher.testDispatcher) {
            onGetCachedCurrencyRateData_thenReturn(null)
            onGetRemoteCurrencyRateList_thenReturnError(IOException("Network error"))

            assertFailsWith<IOException> {
                repository.getExchangeRates()
            }
        }
}