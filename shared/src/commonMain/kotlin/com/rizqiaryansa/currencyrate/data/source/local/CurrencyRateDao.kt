package com.rizqiaryansa.currencyrate.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CurrencyRateDao {

    @Query("SELECT * FROM currency_country WHERE id = :id")
    suspend fun getCurrencyCountryData(
        id: Int = CurrencyCountryData.CACHE_ROW_ID
    ): CurrencyCountryData?

    @Upsert
    suspend fun upsertCurrencyCountry(data: CurrencyCountryData)

    @Query("SELECT * FROM currency_rates WHERE id = :id")
    suspend fun getCurrencyRateData(
        id: Int = CurrencyRateData.CACHE_ROW_ID
    ): CurrencyRateData?

    @Upsert
    suspend fun upsertCurrencyRate(data: CurrencyRateData)
}