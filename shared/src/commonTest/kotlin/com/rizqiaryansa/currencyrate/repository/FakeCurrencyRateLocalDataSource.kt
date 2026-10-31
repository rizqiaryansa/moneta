package com.rizqiaryansa.currencyrate.repository

import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateData
import com.rizqiaryansa.currencyrate.data.source.local.currencyrate.CurrencyRateLocalDataSource

class FakeCurrencyRateLocalDataSource: CurrencyRateLocalDataSource {

    var cachedData: CurrencyRateData? = null
    var getCurrencyRateDataCallCount = 0
    var lastUpsertedData: CurrencyRateData? = null

    override suspend fun getCurrencyRateData(): CurrencyRateData? {
        getCurrencyRateDataCallCount++
        return cachedData
    }

    override suspend fun upsertCurrencyRates(currencyRateData: CurrencyRateData) {
        lastUpsertedData = currencyRateData
        cachedData = currencyRateData
    }

}