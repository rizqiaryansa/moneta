package com.rizqiaryansa.currencyrate.domain.model

data class ExchangeRate(
    val targetCurrencyCode: String,
    val rate: Double
)