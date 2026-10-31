package com.rizqiaryansa.currencyrate.presentation.uimodel

import androidx.compose.runtime.Immutable

@Immutable
data class CurrencyRateUiModel(
    val countryLetter: String,
    val currencyCode: String,
    val countryName: String,
    val rate: String
)