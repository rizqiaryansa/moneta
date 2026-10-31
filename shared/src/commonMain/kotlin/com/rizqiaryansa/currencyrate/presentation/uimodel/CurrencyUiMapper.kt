package com.rizqiaryansa.currencyrate.presentation.uimodel

import com.rizqiaryansa.currencyrate.domain.model.ConvertedCurrency
import com.rizqiaryansa.currencyrate.domain.model.Currency
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList


fun Currency.toUiModel(): CurrencyCountryUiModel {
    return CurrencyCountryUiModel(
        countryLetter = name.toCurrencyLetter(),
        currencyCode = code,
        countryName = name
    )
}

fun List<Currency>.toCurrencyCountryUiModels(): ImmutableList<CurrencyCountryUiModel> {
    return map { it.toUiModel() }.toImmutableList()
}

fun ConvertedCurrency.toUiModel(): CurrencyRateUiModel {
    return CurrencyRateUiModel(
        currencyCode = currency.code,
        countryName = currency.name,
        countryLetter = currency.name.toCurrencyLetter(),
        rate = convertedAmount.toString()
    )
}

fun List<ConvertedCurrency>.toCurrencyRateUiModels(): ImmutableList<CurrencyRateUiModel> {
    return map { it.toUiModel() }.toImmutableList()
}

fun String.toCurrencyLetter(): String {
    return firstOrNull()
        ?.uppercaseChar()
        ?.toString()
        .orEmpty()
}