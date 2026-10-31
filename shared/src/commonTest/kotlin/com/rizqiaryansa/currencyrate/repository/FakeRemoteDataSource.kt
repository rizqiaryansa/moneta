package com.rizqiaryansa.currencyrate.repository

import com.rizqiaryansa.currencyrate.data.source.remote.CurrencyRateRemoteDataSource

class FakeRemoteDataSource: CurrencyRateRemoteDataSource {

    var currencyListResult : Map<String, String> = emptyMap()
    var currencyListError: Throwable? = null
    var getCurrencyListCallCount = 0

    var currencyRateListResult: Map<String, Double> = emptyMap()
    var currencyRateListError: Throwable? = null
    var getCurrencyRateListCallCount = 0

    override suspend fun getCurrencyList(): Map<String, String> {
        getCurrencyListCallCount++
        currencyListError?.let { throw it }
        return currencyListResult
    }

    override suspend fun getCurrencyRateList(): Map<String, Double> {
        getCurrencyRateListCallCount++
        currencyRateListError?.let { throw it }
        return currencyRateListResult
    }

}