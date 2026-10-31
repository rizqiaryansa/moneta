package com.rizqiaryansa.currencyrate.data.source.local.currencylist

import com.rizqiaryansa.currencyrate.data.source.local.CurrencyCountryData

interface CurrencyListLocalDataSource {
    suspend fun getCurrencyCountryData(): CurrencyCountryData?

    suspend fun upsertCurrencyList(data: CurrencyCountryData)
}