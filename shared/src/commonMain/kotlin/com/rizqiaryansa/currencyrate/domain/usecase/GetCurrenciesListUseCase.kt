package com.rizqiaryansa.currencyrate.domain.usecase

import com.rizqiaryansa.currencyrate.domain.CurrencyRateRepository
import com.rizqiaryansa.currencyrate.domain.model.Currency

class GetCurrenciesListUseCase(
    private val repository: CurrencyRateRepository
) {
    suspend operator fun invoke(): List<Currency> {
        return repository
            .getCurrencies()
            .sortedBy { it.name }
    }
}