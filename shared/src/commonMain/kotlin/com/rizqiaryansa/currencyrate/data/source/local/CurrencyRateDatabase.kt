package com.rizqiaryansa.currencyrate.data.source.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.rizqiaryansa.currencyrate.data.source.local.converters.CurrencyRateConverters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [CurrencyCountryData::class, CurrencyRateData::class], version = 1)
@TypeConverters(CurrencyRateConverters::class)
@ConstructedBy(CurrencyRateDatabaseConstructor::class)
abstract class CurrencyRateDatabase: RoomDatabase() {

    abstract fun currencyRateDao(): CurrencyRateDao

    companion object {
        const val DB_NAME = "currency_rate.db"
    }
}

// Room KSP automatically generates the `actual` implementation for Android and iOS
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING", "NO_ACTUAL_FOR_EXPECT", "KotlinNoActualForExpect")
expect object CurrencyRateDatabaseConstructor: RoomDatabaseConstructor<CurrencyRateDatabase> {
    override fun initialize(): CurrencyRateDatabase
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<CurrencyRateDatabase>
): CurrencyRateDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}