package com.rizqiaryansa.currencyrate

import androidx.compose.ui.window.ComposeUIViewController
import com.rizqiaryansa.currencyrate.di.IosAppGraph
import dev.zacsweers.metro.createGraph

private val iosAppGraph: IosAppGraph by lazy { createGraph<IosAppGraph>() }

fun MainViewController() = ComposeUIViewController {
    App(appGraph = iosAppGraph)
}