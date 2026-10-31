package com.rizqiaryansa.currencyrate.data.source.remote

import com.rizqiaryansa.currencyrate.data.source.remote.model.CurrencyRateResponse


interface CurrencyRateApiService {
    suspend fun getCurrencyRateList(
        appId: String,
        base: String = USD
    ): CurrencyRateResponse

    suspend fun getCurrencyList(
        appId: String
    ): Map<String, String>

    companion object {
        const val USD = "USD"
    }
}