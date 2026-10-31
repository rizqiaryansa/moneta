package com.rizqiaryansa.currencyrate.repository

import com.rizqiaryansa.currencyrate.data.CurrencyRateRepositoryImpl
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyCountryData
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateData
import com.rizqiaryansa.currencyrate.domain.CurrencyRateRepository
import com.rizqiaryansa.currencyrate.utils.StandardCoroutineRule
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

open class CurrencyRateRepositoryTestFixture {

    val rule = StandardCoroutineRule()

    lateinit var remoteDataSource: FakeRemoteDataSource
    lateinit var currencyListLocalDataSource: FakeCurrencyListLocalDataSource
    lateinit var rateLocalDataSource: FakeCurrencyRateLocalDataSource
    lateinit var timeProvider: FakeTimeProvider
    lateinit var repository: CurrencyRateRepository
    
    @BeforeTest
    fun setup() {
        rule.starting()
        remoteDataSource = FakeRemoteDataSource()
        currencyListLocalDataSource = FakeCurrencyListLocalDataSource()
        rateLocalDataSource = FakeCurrencyRateLocalDataSource()
        timeProvider = FakeTimeProvider()
        repository = CurrencyRateRepositoryImpl(
            remoteDataSource = remoteDataSource,
            currencyListLocalDataSource = currencyListLocalDataSource,
            rateLocalDataSource = rateLocalDataSource,
            timeProvider = timeProvider,
            dispatcher = rule.dispatcher
        )
    }
    
    @AfterTest
    fun finish() {
        rule.finished()
    }

    // Remote mock helpers
    protected fun onGetRemoteCurrencyList_thenReturn(currencyMap: Map<String, String>) {
        remoteDataSource.currencyListResult = currencyMap
    }

    protected fun onGetRemoteCurrencyList_thenReturnError(throwable: Throwable) {
        remoteDataSource.currencyListError = throwable
    }

    protected fun verifyGetRemoteCurrencyList(exactly: Int = 1) {
        assertEquals(exactly, remoteDataSource.getCurrencyListCallCount)
    }

    protected fun onGetRemoteCurrencyRateList_thenReturn(ratesMap: Map<String, Double>) {
        remoteDataSource.currencyRateListResult = ratesMap
    }

    protected fun onGetRemoteCurrencyRateList_thenReturnError(throwable: Throwable) {
        remoteDataSource.currencyRateListError = throwable
    }

    protected fun verifyGetRemoteCurrencyRateList(exactly: Int = 1) {
        assertEquals(exactly, remoteDataSource.getCurrencyRateListCallCount)
    }
    // Local currency list helpers
    protected fun onGetCachedCurrencyCountryData_thenReturn(data: CurrencyCountryData?) {
        currencyListLocalDataSource.cachedData = data
    }

    protected fun verifyGetCachedCurrencyCountryData() {
        assertTrue(currencyListLocalDataSource.getCurrencyCountryDataCallCount > 0)
    }

    protected fun onUpsertCurrencyList_thenReturnUnit() {
        // No-op for Fake; upsert is recorded automatically
    }
    protected fun verifyUpsertCurrencyList(data: CurrencyCountryData) {
        assertEquals(data, currencyListLocalDataSource.lastUpsertedData)
    }

    // Local currency rate helpers
    protected fun onGetCachedCurrencyRateData_thenReturn(data: CurrencyRateData?) {
        rateLocalDataSource.cachedData = data
    }

    protected fun verifyGetCachedCurrencyRateData() {
        assertTrue(rateLocalDataSource.getCurrencyRateDataCallCount > 0)
    }

    protected fun onUpsertCurrencyRates_thenReturnUnit() {
        // No-op for Fake; upsert is recorded automatically
    }

    protected fun verifyUpsertCurrencyRates(data: CurrencyRateData) {
        assertEquals(data, rateLocalDataSource.lastUpsertedData)
    }
    protected fun onGetCurrentTimeInMillis_thenReturn(currentTimeInMillis: Long) {
        timeProvider.currentTimeMillis = currentTimeInMillis
    }
    
}