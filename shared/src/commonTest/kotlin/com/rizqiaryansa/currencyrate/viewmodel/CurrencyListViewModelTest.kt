package com.rizqiaryansa.currencyrate.viewmodel

import app.cash.turbine.test
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyListStatus
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyListUiAction
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyListUiEffect
import com.rizqiaryansa.currencyrate.presentation.uimodel.toCurrencyCountryUiModels
import com.rizqiaryansa.currencyrate.utils.getDomainCurrencies
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CurrencyListViewModelTest : CurrencyListViewModelTestFixture() {

    @Test
    fun when_opened_then_fetches_full_currency_list_on_initial_load() =
        runTest(rule.dispatcher.testDispatcher) {
            val domainCurrencies = getDomainCurrencies()
            onGetCurrenciesList_thenReturn(domainCurrencies)

            val expectedCurrencies = domainCurrencies.sortedBy { it.name }

            val testVm = createViewModel()
            testVm.uiState.test {
                assertEquals(CurrencyListStatus.Loading, awaitItem().currencyListStatus)

                testScheduler.advanceTimeBy(350L)
                testScheduler.advanceUntilIdle()

                val state = awaitItem()
                assertEquals("", state.searchQuery)
                assertEquals(
                    CurrencyListStatus.Success(expectedCurrencies.toCurrencyCountryUiModels()),
                    state.currencyListStatus
                )
                cancelAndIgnoreRemainingEvents()
            }
            verifyGetCurrenciesListUseCaseCalled()
        }

    @Test
    fun when_initial_load_fails_then_currencyListStatus_is_Error() =
        runTest(rule.dispatcher.testDispatcher) {
            val errorMessage = "Failed to load currencies"
            onGetCurrenciesList_thenReturnError(RuntimeException(errorMessage))

            val testVm = createViewModel()
            testVm.uiState.test {
                assertEquals(CurrencyListStatus.Loading, awaitItem().currencyListStatus)

                testScheduler.advanceTimeBy(350L)
                testScheduler.advanceUntilIdle()

                val state = awaitItem()
                assertEquals(CurrencyListStatus.Error(errorMessage), state.currencyListStatus)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun when_search_query_changed_then_debounces_and_calls_searchCurrenciesUseCase() =
        runTest(rule.dispatcher.testDispatcher) {
            val domainCurrencies =
                getDomainCurrencies().filter { it.code.contains("JPY", ignoreCase = true) }
            onSearchCurrencies_thenReturn("JPY", domainCurrencies)

            viewModel.uiState.test {
                awaitItem() // Initial Loading

                viewModel.onAction(CurrencyListUiAction.OnSearchQueryChanged("JPY"))

                val typedState = awaitItem()
                assertEquals("JPY", typedState.searchQuery)

                testScheduler.advanceTimeBy(350L)
                testScheduler.advanceUntilIdle()

                val state = awaitItem()
                assertEquals("JPY", state.searchQuery)
                assertEquals(
                    CurrencyListStatus.Success(domainCurrencies.toCurrencyCountryUiModels()),
                    state.currencyListStatus
                )
                cancelAndIgnoreRemainingEvents()
            }
            verifySearchCurrenciesUseCaseCalled("JPY")
        }

    @Test
    fun when_search_query_error_then_currencyListStatus_is_Error() =
        runTest(rule.dispatcher.testDispatcher) {
            val errorMessage = "Search failure"
            onSearchCurrencies_thenReturnError("XYZ", RuntimeException(errorMessage))

            viewModel.uiState.test {
                awaitItem() // Initial Loading

                viewModel.onAction(CurrencyListUiAction.OnSearchQueryChanged("XYZ"))
                val typedState = awaitItem()
                assertEquals("XYZ", typedState.searchQuery)

                testScheduler.advanceTimeBy(350L)
                testScheduler.advanceUntilIdle()

                val state = awaitItem()
                assertEquals(CurrencyListStatus.Error(errorMessage), state.currencyListStatus)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun when_OnCountrySelected_then_emits_NavigateBackWithCountrySelected_effect() =
        runTest(rule.dispatcher.testDispatcher) {
            viewModel.uiEffect.test {
                viewModel.onAction(CurrencyListUiAction.OnCountrySelected("USD"))
                testScheduler.advanceUntilIdle()

                val effect = awaitItem()
                assertEquals(
                    CurrencyListUiEffect.NavigationBackWithCountrySelected("USD"),
                    effect
                )
                cancelAndIgnoreRemainingEvents()
            }
        }
}