package com.rizqiaryansa.currencyrate.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyRateResponse(
    @SerialName("rates")
    val rates: Map<String, Double> = mapOf()
)