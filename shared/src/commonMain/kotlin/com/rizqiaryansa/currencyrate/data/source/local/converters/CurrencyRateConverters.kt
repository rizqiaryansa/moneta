package com.rizqiaryansa.currencyrate.data.source.local.converters

import androidx.room.TypeConverter
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyCountryEntity
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateEntity
import kotlinx.serialization.json.Json

class CurrencyRateConverters {

    @TypeConverter
    fun fromCurrencyCountryEntityList(list: List<CurrencyCountryEntity>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun toCurrencyCountryEntityList(value: String): List<CurrencyCountryEntity> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromCurrencyRateEntityList(list: List<CurrencyRateEntity>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun toCurrencyEntityList(value: String): List<CurrencyRateEntity> {
        return Json.decodeFromString(value)
    }
}