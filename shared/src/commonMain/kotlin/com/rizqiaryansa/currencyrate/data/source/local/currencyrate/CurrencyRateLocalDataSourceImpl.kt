package com.rizqiaryansa.currencyrate.data.source.local.currencyrate

import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateDao
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateData
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject

@ContributesBinding(AppScope::class)
@Inject
class CurrencyRateLocalDataSourceImpl(
    private val dao: CurrencyRateDao
): CurrencyRateLocalDataSource {
    override suspend fun getCurrencyRateData(): CurrencyRateData? {
        return dao.getCurrencyRateData()
    }

    override suspend fun upsertCurrencyRates(currencyRateData: CurrencyRateData) {
        dao.upsertCurrencyRate(currencyRateData)
    }

}