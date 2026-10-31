package com.rizqiaryansa.currencyrate.di

import androidx.room.RoomDatabase
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateDatabase
import com.rizqiaryansa.currencyrate.data.source.local.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<RoomDatabase.Builder<CurrencyRateDatabase>> {
        getDatabaseBuilder()
    }
}