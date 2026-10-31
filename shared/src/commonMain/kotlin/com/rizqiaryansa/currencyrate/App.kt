package com.rizqiaryansa.currencyrate

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rizqiaryansa.currencyrate.presentation.screen.CurrencyRateAppWithSplash
import com.rizqiaryansa.currencyrate.ui.theme.CurrencyRateTheme

@Composable
@Preview
fun App() {
    CurrencyRateTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            CurrencyRateAppWithSplash(innerPadding)
        }
    }
}