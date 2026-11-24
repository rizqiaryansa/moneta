package com.rizqiaryansa.currencyrate.data.source.remote

import com.rizqiaryansa.currencyrate.BuildConfig
import com.rizqiaryansa.currencyrate.data.source.remote.model.CurrencyRateResponse
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
@Inject
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