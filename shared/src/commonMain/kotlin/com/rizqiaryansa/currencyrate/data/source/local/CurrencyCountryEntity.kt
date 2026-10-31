package com.rizqiaryansa.currencyrate.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rizqiaryansa.currencyrate.data.source.isExpiredTime
import kotlinx.serialization.Serializable

@Entity(tableName = "currency_country")
data class CurrencyCountryData(
    @PrimaryKey val id: Int = CACHE_ROW_ID,
    val timeStamp: Long,
    val currencyCountryList: List<CurrencyCountryEntity> // stored as JSON using TypeConverter
) {

    fun isUsable(): Boolean {
        return currencyCountryList.isNotEmpty() && !timeStamp.isExpiredTime()
    }

    companion object {
        const val CACHE_ROW_ID = 1
    }
}

@Serializable
data class CurrencyCountryEntity(
    val currencyCode: String,
    val currencyName: String
)