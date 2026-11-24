package com.rizqiaryansa.currencyrate

import android.app.Application
import com.rizqiaryansa.currencyrate.di.AndroidAppGraph
import dev.zacsweers.metro.createGraph
import dev.zacsweers.metro.createGraphFactory

class CurrencyRateApplication: Application() {

    val appGraph: AndroidAppGraph by lazy {
        createGraphFactory<AndroidAppGraph.Factory>().create(applicationContext)
    }
}