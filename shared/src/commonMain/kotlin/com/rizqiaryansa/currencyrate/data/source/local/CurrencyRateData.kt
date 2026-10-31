package com.rizqiaryansa.currencyrate.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rizqiaryansa.currencyrate.data.source.isExpiredTime
import kotlinx.serialization.Serializable
import kotlin.time.Clock

@Entity(tableName = "currency_rates")
data class CurrencyRateData(
    @PrimaryKey val id: Int = CACHE_ROW_ID,
    val timeStamp: Long = Clock.System.now().toEpochMilliseconds(),
    val currencyRateEntityList: List<CurrencyRateEntity>
){

    fun isUsable(): Boolean {
        return currencyRateEntityList.isNotEmpty() && !timeStamp.isExpiredTime()
    }

    companion object {
        const val CACHE_ROW_ID = 1
    }
}

@Serializable
data class CurrencyRateEntity(
    val currencyCode: String,
    val rate: Double
)