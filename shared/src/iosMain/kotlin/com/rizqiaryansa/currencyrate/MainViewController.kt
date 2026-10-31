package com.rizqiaryansa.currencyrate

import androidx.compose.ui.window.ComposeUIViewController
import com.rizqiaryansa.currencyrate.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}