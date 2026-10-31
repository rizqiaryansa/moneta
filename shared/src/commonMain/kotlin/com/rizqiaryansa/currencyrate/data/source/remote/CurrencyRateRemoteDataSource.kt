package com.rizqiaryansa.currencyrate.data.source.remote

interface CurrencyRateRemoteDataSource {
    suspend fun getCurrencyList(): Map<String, String>
    suspend fun getCurrencyRateList(): Map<String, Double>
}