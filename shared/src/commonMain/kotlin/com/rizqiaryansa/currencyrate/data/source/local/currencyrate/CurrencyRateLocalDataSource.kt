package com.rizqiaryansa.currencyrate.data.source.local.currencyrate

import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateData

interface CurrencyRateLocalDataSource {
    suspend fun getCurrencyRateData(): CurrencyRateData?

    suspend fun upsertCurrencyRates(currencyRateData: CurrencyRateData)
}