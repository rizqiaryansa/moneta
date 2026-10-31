package com.rizqiaryansa.currencyrate.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<CurrencyRateDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(CurrencyRateDatabase.DB_NAME)
    return Room.databaseBuilder<CurrencyRateDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}