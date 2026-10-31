package com.rizqiaryansa.currencyrate.repository

import com.rizqiaryansa.currencyrate.domain.CurrencyRateRepository
import com.rizqiaryansa.currencyrate.domain.model.Currency
import com.rizqiaryansa.currencyrate.domain.model.ExchangeRate

class FakeCurrencyRateRepository : CurrencyRateRepository {

    var currenciesToReturn: List<Currency> = emptyList()
    var currenciesError: Throwable? = null
    var getCurrenciesCallCount: Int = 0

    var exchangeRatesToReturn: List<ExchangeRate> = emptyList()
    var exchangeRatesError: Throwable? = null
    var getExchangeRatesCallCount: Int = 0

    private val exchangeRateResponses =
        mutableMapOf<Pair<Double, String>, Result<List<ExchangeRate>>>()

    fun setExchangeRatesResponse(
        amount: Double,
        baseCurrency: String,
        result: Result<List<ExchangeRate>>
    ) {
        exchangeRateResponses[amount to baseCurrency] = result
    }

    override suspend fun getCurrencies(): List<Currency> {
        getCurrenciesCallCount++
        currenciesError?.let { throw it }
        return currenciesToReturn
    }

    override suspend fun getExchangeRates(): List<ExchangeRate> {
        val callIndex = getExchangeRatesCallCount++

        if (exchangeRateResponses.isNotEmpty()) {
            val responses = exchangeRateResponses.values.toList()
            return responses.getOrElse(callIndex) { responses.last() }.getOrThrow()
        }

        exchangeRatesError?.let { throw it }
        return exchangeRatesToReturn
    }
}