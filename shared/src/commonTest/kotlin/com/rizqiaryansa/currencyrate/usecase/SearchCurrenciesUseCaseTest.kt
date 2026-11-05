package com.rizqiaryansa.currencyrate.usecase

import com.rizqiaryansa.currencyrate.domain.model.Currency
import com.rizqiaryansa.currencyrate.domain.usecase.SearchCurrenciesUseCase
import com.rizqiaryansa.currencyrate.repository.FakeCurrencyRateRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchCurrenciesUseCaseTest {

    private lateinit var repository: FakeCurrencyRateRepository
    private lateinit var useCase: SearchCurrenciesUseCase

    @BeforeTest
    fun setup() {
        repository = FakeCurrencyRateRepository()
        useCase = SearchCurrenciesUseCase(repository)
    }

    @Test
    fun when_matching_code_or_name_then_returns_filtered_currencies() = runTest {
        val currencies = listOf(
            Currency("JPY", "Japanese Yen"),
            Currency("USD", "US Dollar"),
            Currency("EUR", "Euro")
        )
        repository.currenciesToReturn = currencies

        val resultByCode = useCase.invoke("jp")
        assertEquals(1, resultByCode.size)
        assertEquals("JPY", resultByCode[0].code)

        val resultByName = useCase.invoke("dollar")
        assertEquals(1, resultByName.size)
        assertEquals("USD", resultByName[0].code)
    }

    @Test
    fun when_no_match_found_then_returns_empty_list() = runTest {
        val currencies = listOf(
            Currency("JPY", "Japanese Yen"),
            Currency("USD", "US Dollar")
        )
        repository.currenciesToReturn = currencies

        val result = useCase.invoke("XYZ")
        assertTrue(result.isEmpty())
    }
}