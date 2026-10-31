package com.rizqiaryansa.currencyrate.domain

import com.rizqiaryansa.currencyrate.domain.model.Currency
import com.rizqiaryansa.currencyrate.domain.model.ExchangeRate

interface CurrencyRateRepository {

    suspend fun getCurrencies(): List<Currency>

    suspend fun getExchangeRates(): List<ExchangeRate>
}