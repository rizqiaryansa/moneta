package com.rizqiaryansa.currencyrate.di

import androidx.room.RoomDatabase
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateDatabase
import com.rizqiaryansa.currencyrate.data.source.local.getDatabaseBuilder
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn

@DependencyGraph(AppScope::class)
interface IosAppGraph: AppGraph {

    @Provides
    @SingleIn(AppScope::class)
    fun provideDatabaseBuilder(): RoomDatabase.Builder<CurrencyRateDatabase> = getDatabaseBuilder()
}