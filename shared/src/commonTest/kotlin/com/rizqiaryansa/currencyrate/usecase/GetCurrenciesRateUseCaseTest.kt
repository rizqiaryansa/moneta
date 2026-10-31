package com.rizqiaryansa.currencyrate.usecase

import com.rizqiaryansa.currencyrate.domain.model.Currency
import com.rizqiaryansa.currencyrate.domain.model.ExchangeRate
import com.rizqiaryansa.currencyrate.domain.usecase.GetCurrenciesRateUseCase
import com.rizqiaryansa.currencyrate.repository.FakeCurrencyRateRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class GetCurrenciesRateUseCaseTest {

    private lateinit var repository: FakeCurrencyRateRepository
    private lateinit var useCase: GetCurrenciesRateUseCase

    @BeforeTest
    fun setup() {
        repository = FakeCurrencyRateRepository()
        useCase = GetCurrenciesRateUseCase(repository)
    }

    @Test
    fun `when exchangeRates is empty then returns empty list`() {
        runTest {
            repository.currenciesToReturn = listOf(Currency("USD", "US Dollar"))
            repository.exchangeRatesToReturn = emptyList()

            val result = useCase(10.0, "USD")
            assertTrue(result.isEmpty())
        }
    }

    @Test
    fun `when selected base currency is unsupported then throws IllegalArgumentException`() {
        runTest {
            repository.currenciesToReturn = listOf(Currency("USD", "US Dollar"))
            repository.exchangeRatesToReturn = listOf(ExchangeRate("USD", 1.0))

            assertFailsWith<IllegalArgumentException> {
                useCase(10.0, "")
            }
        }
    }

    @Test
    fun `when valid input calculates and converted amounts and sorts by currency name`() {
        runTest {
            val currencies = listOf(
                Currency("JPY", "Japanese Yen"),
                Currency("EUR", "Euro"),
                Currency("USD", "US Dollar"),
            )

            val exchangeRates = listOf(
                ExchangeRate("USD", 1.0),
                ExchangeRate("EUR", 0.8),
                ExchangeRate("JPY", 150.0),
            )

            repository.currenciesToReturn = currencies
            repository.exchangeRatesToReturn = exchangeRates

            val result = useCase(10.0, "EUR")

            assertEquals(3, result.size)

            // Sorted by currency name: Euro, Japanese Yen, US Dollar
            assertEquals("EUR", result[0].currency.code)
            assertEquals(10.0, result[0].convertedAmount, 0.001)

            assertEquals("JPY", result[1].currency.code)
            assertEquals(1875.0, result[1].convertedAmount, 0.001)

            assertEquals("USD", result[2].currency.code)
            assertEquals(12.5, result[2].convertedAmount, 0.001)
        }
    }
}