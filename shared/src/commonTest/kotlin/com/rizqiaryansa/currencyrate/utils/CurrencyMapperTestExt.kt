package com.rizqiaryansa.currencyrate.utils

import com.rizqiaryansa.currencyrate.data.source.local.CurrencyCountryEntity
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateEntity
import com.rizqiaryansa.currencyrate.data.source.remote.model.CurrencyRateResponse
import com.rizqiaryansa.currencyrate.domain.model.ConvertedCurrency
import com.rizqiaryansa.currencyrate.domain.model.Currency
import com.rizqiaryansa.currencyrate.domain.model.ExchangeRate
import com.rizqiaryansa.currencyrate.presentation.uimodel.CurrencyCountryUiModel
import com.rizqiaryansa.currencyrate.presentation.uimodel.CurrencyRateUiModel
import com.rizqiaryansa.currencyrate.presentation.uimodel.toCurrencyLetter

const val CURRENCY_RATE_JSON = "json/mock_currency_rate.json"
const val CURRENCY_LIST_JSON = "json/mock_currency_list.json"

fun getCurrencyMap(): Map<String, String> {
    return createSuccessResponse<Map<String, String>>(CURRENCY_LIST_JSON)
}

fun getCurrencyRateMap(): Map<String, Double> {
    return createSuccessResponse<CurrencyRateResponse>(CURRENCY_RATE_JSON).rates
}

fun getDomainCurrencies(): List<Currency> {
    return getCurrencyMap().map { Currency(code = it.key, name = it.value) }
}

fun getDomainExchangeRates(): List<ExchangeRate> {
    return getCurrencyRateMap().map { ExchangeRate(targetCurrencyCode = it.key, rate = it.value) }
}

fun getCurrencyCountryEntities(): List<CurrencyCountryEntity> {
    return getCurrencyMap().map {
        CurrencyCountryEntity(currencyCode = it.key.uppercase(), currencyName = it.value)
    }
}

fun getCurrencyRateEntities(): List<CurrencyRateEntity> {
    return getCurrencyRateMap().map {
        CurrencyRateEntity(currencyCode = it.key, rate = it.value)
    }
}

fun getCurrencyCountryUiModelList(): List<CurrencyCountryUiModel> {
    return getDomainCurrencies().map {
        CurrencyCountryUiModel(
            countryLetter = it.name.toCurrencyLetter(),
            currencyCode = it.code,
            countryName = it.name
        )
    }
}

fun getCurrencyCountryUiModelListByKeyword(keyword: String): List<CurrencyCountryUiModel> {
    return getCurrencyCountryUiModelList().filter {
        it.currencyCode.contains(keyword, ignoreCase = true) ||
            it.countryName.contains(keyword, ignoreCase = true)
    }
}

fun getConvertedCurrencyList(
    amount: Double = 1.0,
    selectedBaseCurrency: String = "USD"
): List<ConvertedCurrency> {
    val rates = getCurrencyRateMap()
    val baseRate = rates[selectedBaseCurrency] ?: 1.0
    val amountInBase = amount / baseRate
    return getDomainCurrencies().mapNotNull { currency ->
        val targetRate = rates[currency.code] ?: return@mapNotNull null
        ConvertedCurrency(
            currency = currency,
            convertedAmount = amountInBase * targetRate
        )
    }.sortedBy { it.currency.name }
}

fun getCurrencyRateUiModelList(): List<CurrencyRateUiModel> {
    return getConvertedCurrencyList().map {
        CurrencyRateUiModel(
            countryLetter = it.currency.name.toCurrencyLetter(),
            currencyCode = it.currency.code,
            countryName = it.currency.name,
            rate = it.convertedAmount.toString()
        )
    }
}