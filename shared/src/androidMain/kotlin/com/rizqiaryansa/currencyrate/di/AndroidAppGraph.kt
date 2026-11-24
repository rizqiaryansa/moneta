package com.rizqiaryansa.currencyrate.di

import android.content.Context
import androidx.room.RoomDatabase
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateDatabase
import com.rizqiaryansa.currencyrate.data.source.local.getDatabaseBuilder
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn

@DependencyGraph(AppScope::class)
interface AndroidAppGraph: AppGraph {

    @Provides
    @SingleIn(AppScope::class)
    fun provideDatabaseBuilder(context: Context)
    : RoomDatabase.Builder<CurrencyRateDatabase> =
        getDatabaseBuilder(context)

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Provides context: Context): AndroidAppGraph
    }
}