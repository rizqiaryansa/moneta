package com.rizqiaryansa.currencyrate.usecase

import com.rizqiaryansa.currencyrate.domain.model.Currency
import com.rizqiaryansa.currencyrate.domain.usecase.GetCurrenciesListUseCase
import com.rizqiaryansa.currencyrate.repository.FakeCurrencyRateRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetCurrenciesListUseCaseTest {

    private lateinit var repository: FakeCurrencyRateRepository
    private lateinit var useCase: GetCurrenciesListUseCase

    @BeforeTest
    fun setup() {
        repository = FakeCurrencyRateRepository()
        useCase = GetCurrenciesListUseCase(repository)
    }

    @Test
    fun when_getCurrenciesList_then_returns_currencies_sorted_alphabetically_by_name() = runTest {
        val unsorted = listOf(
            Currency("USD", "US Dollar"),
            Currency("AUD", "Australian Dollar"),
            Currency("EUR", "Euro")
        )
        repository.currenciesToReturn = unsorted

        val result = useCase.invoke()

        assertEquals(3, result.size)
        assertEquals("Australian Dollar", result[0].name)
        assertEquals("Euro", result[1].name)
        assertEquals("US Dollar", result[2].name)
    }
}