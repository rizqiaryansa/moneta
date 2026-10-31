package com.rizqiaryansa.currencyrate.domain.usecase

import com.rizqiaryansa.currencyrate.domain.CurrencyRateRepository
import com.rizqiaryansa.currencyrate.domain.model.ConvertedCurrency
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetCurrenciesRateUseCase(
    private val repo: CurrencyRateRepository
) {
    suspend operator fun invoke(
        amount: Double,
        selectedBaseCurrency: String
    ): List<ConvertedCurrency> {
        return coroutineScope {
            val selectedCode = selectedBaseCurrency.trim().uppercase()
            val currenciesDeferred = async { repo.getCurrencies() }
            val exchangeRatesDeferred = async { repo.getExchangeRates() }

            val currencies = currenciesDeferred.await()
            val exchangeRates = exchangeRatesDeferred.await()

            if (exchangeRates.isEmpty()) return@coroutineScope emptyList()

            val ratesByTargetCode = exchangeRates.associateBy { it.targetCurrencyCode }

            val selectedRate =
                ratesByTargetCode[selectedCode]?.rate ?: throw IllegalArgumentException(
                    "Unsupported currency: $selectedCode"
                )

            val amountInApiBaseCurrency = amount / selectedRate

            currencies.mapNotNull { currency ->
                val targetRate = ratesByTargetCode[currency.code]?.rate ?: return@mapNotNull null

                ConvertedCurrency(
                    currency = currency,
                    convertedAmount = amountInApiBaseCurrency * targetRate
                )
            }.sortedBy { it.currency.name }
        }
    }
}