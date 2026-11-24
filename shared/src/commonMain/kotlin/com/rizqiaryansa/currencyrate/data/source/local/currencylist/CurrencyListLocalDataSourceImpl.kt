package com.rizqiaryansa.currencyrate.data.source.local.currencylist

import com.rizqiaryansa.currencyrate.data.source.local.CurrencyCountryData
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateDao
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject

@ContributesBinding(AppScope::class)
@Inject
class CurrencyListLocalDataSourceImpl(
    private val dao: CurrencyRateDao
) : CurrencyListLocalDataSource {

    override suspend fun getCurrencyCountryData(): CurrencyCountryData? {
        return dao.getCurrencyCountryData()
    }

    override suspend fun upsertCurrencyList(data: CurrencyCountryData) {
        dao.upsertCurrencyCountry(data)
    }
}