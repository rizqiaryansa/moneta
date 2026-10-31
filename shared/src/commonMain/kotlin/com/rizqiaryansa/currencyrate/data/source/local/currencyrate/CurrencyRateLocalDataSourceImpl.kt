package com.rizqiaryansa.currencyrate.data.source.local.currencyrate

import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateDao
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateData

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