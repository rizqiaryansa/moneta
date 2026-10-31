package com.rizqiaryansa.currencyrate.repository

import com.rizqiaryansa.currencyrate.data.source.local.CurrencyCountryData
import com.rizqiaryansa.currencyrate.data.source.local.currencylist.CurrencyListLocalDataSource

class FakeCurrencyListLocalDataSource: CurrencyListLocalDataSource {

    var cachedData: CurrencyCountryData? = null
    var getCurrencyCountryDataCallCount = 0
    var lastUpsertedData: CurrencyCountryData? = null

    override suspend fun getCurrencyCountryData(): CurrencyCountryData? {
        getCurrencyCountryDataCallCount++
        return cachedData
    }

    override suspend fun upsertCurrencyList(data: CurrencyCountryData) {
        lastUpsertedData = data
        cachedData = data
    }

}