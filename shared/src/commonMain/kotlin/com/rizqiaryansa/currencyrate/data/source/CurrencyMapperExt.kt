package com.rizqiaryansa.currencyrate.data.source

import com.rizqiaryansa.currencyrate.data.source.local.CurrencyCountryData
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyCountryEntity
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateData
import com.rizqiaryansa.currencyrate.data.source.local.CurrencyRateEntity
import com.rizqiaryansa.currencyrate.domain.model.Currency
import com.rizqiaryansa.currencyrate.domain.model.ExchangeRate
import kotlin.time.Clock
import kotlin.time.Duration.Companion.minutes

fun Map<String, String>.toCurrencyCountryEntities(): List<CurrencyCountryEntity> {
    return map { (code, name) ->
        CurrencyCountryEntity(
            currencyCode = code.uppercase(),
            currencyName = name
        )
    }
}

fun Map<String, Double>.toCurrencyRateEntities(): List<CurrencyRateEntity> {
    return map { CurrencyRateEntity(currencyCode = it.key, rate = it.value) }
}

fun CurrencyRateData.toDomainExchangeRates(): List<ExchangeRate> {
    return currencyRateEntityList.map {
        ExchangeRate(
            targetCurrencyCode = it.currencyCode,
            rate = it.rate
        )
    }
}

fun CurrencyCountryData.toDomainCurrencies(): List<Currency> {
    return currencyCountryList.map {
        Currency(
            code = it.currencyCode,
            name = it.currencyName
        )
    }
}

fun Long?.isExpiredTime(
    nowTime: Long = Clock.System.now().toEpochMilliseconds()
): Boolean {
    val lastTimeStamp = this ?: return true
    val elapsedTime = nowTime - lastTimeStamp

    val thirtyMinutesInMillis = 30.minutes.inWholeMilliseconds

    return elapsedTime >= thirtyMinutesInMillis
}