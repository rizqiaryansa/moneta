package com.rizqiaryansa.currencyrate.data.source.remote

import com.rizqiaryansa.currencyrate.BuildConfig
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject

@ContributesBinding(AppScope::class)
@Inject
class CurrencyRateRemoteDataSourceImpl(
    private val apiService: CurrencyRateApiService,
    private val appId: String = BuildConfig.APP_ID
): CurrencyRateRemoteDataSource {

    override suspend fun getCurrencyList(): Map<String, String> {
        return apiService.getCurrencyList(appId)
    }

    override suspend fun getCurrencyRateList(): Map<String, Double> {
        return apiService.getCurrencyRateList(appId).rates
    }
}