package com.rizqiaryansa.currencyrate.di

import dev.zacsweers.metrox.viewmodel.MetroViewModelFactory
import dev.zacsweers.metrox.viewmodel.ViewModelGraph

interface AppGraph: ViewModelGraph {
    override val metroViewModelFactory: MetroViewModelFactory
}