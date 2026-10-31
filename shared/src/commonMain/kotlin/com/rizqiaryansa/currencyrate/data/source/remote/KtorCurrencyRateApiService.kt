package com.rizqiaryansa.currencyrate.data.source.remote

import com.rizqiaryansa.currencyrate.BuildConfig
import com.rizqiaryansa.currencyrate.data.source.remote.model.CurrencyRateResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorCurrencyRateApiService(
    private val httpClient: HttpClient
): CurrencyRateApiService {

    override suspend fun getCurrencyRateList(
        appId: String,
        base: String
    ): CurrencyRateResponse {
        return httpClient.get("${BuildConfig.BASE_URL_CURRENCY}latest.json") {
            parameter("app_id", appId)
            parameter("base", base)
        }.body()
    }

    override suspend fun getCurrencyList(appId: String): Map<String, String> {
        return httpClient.get("${BuildConfig.BASE_URL_CURRENCY}currencies.json") {
            parameter("app_id", appId)
        }.body()
    }

}