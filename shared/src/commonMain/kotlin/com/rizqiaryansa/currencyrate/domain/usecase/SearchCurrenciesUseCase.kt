package com.rizqiaryansa.currencyrate.domain.usecase

import com.rizqiaryansa.currencyrate.domain.CurrencyRateRepository
import com.rizqiaryansa.currencyrate.domain.model.Currency

class SearchCurrenciesUseCase(
    private val repository: CurrencyRateRepository
) {
    suspend operator fun invoke(keyword: String): List<Currency> {
        return repository
            .getCurrencies()
            .filter {
                it.code.contains(
                    keyword,
                    ignoreCase = true
                ) || it.name.contains(keyword, ignoreCase = true)
            }
    }
}