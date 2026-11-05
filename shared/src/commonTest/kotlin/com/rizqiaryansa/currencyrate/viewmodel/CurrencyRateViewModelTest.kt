package com.rizqiaryansa.currencyrate.viewmodel

import app.cash.turbine.test
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyRateUiEvent
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.RateStatus
import com.rizqiaryansa.currencyrate.presentation.uimodel.toCurrencyRateUiModels
import com.rizqiaryansa.currencyrate.utils.getConvertedCurrencyList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CurrencyRateViewModelTest : CurrencyRateViewModelTestFixture() {

    @Test
    fun when_init_then_fetches_default_rates_for_USD_and_1() {
        runTest(rule.dispatcher.testDispatcher) {
            val convertedList = getConvertedCurrencyList(1.0, "USD")
            onGetCurrenciesRateList_thenReturn(1.0, "USD", convertedList)

            val viewModel = createViewModel()
            viewModel.uiState.test {
                assertEquals(RateStatus.Loading, awaitItem().rateStatus)

                testScheduler.advanceUntilIdle()

                val state = awaitItem()
                assertEquals("USD", state.selectedCurrencyCode)
                assertEquals("1", state.amountInput)
                assertEquals(
                    RateStatus.Success(convertedList.toCurrencyRateUiModels()),
                    state.rateStatus
                )
                cancelAndIgnoreRemainingEvents()
            }
            verifyGetCurrenciesRateListUseCaseCalled(1.0, "USD")
        }
    }

    @Test
    fun when_OnGetRatesClicked_then_fetches_rates_for_current_currency_and_amount() =
        runTest(rule.dispatcher.testDispatcher) {
            val convertedList = getConvertedCurrencyList(10.0, "USD")
            onGetCurrenciesRateList_thenReturn(10.0, "USD", convertedList)

            viewModel.uiState.test {
                awaitItem() // Initial Loading from init
                testScheduler.advanceUntilIdle()
                awaitItem() // Success from init

                viewModel.onAction(CurrencyRateUiEvent.OnAmountChanged("10.0"))
                val amountState = awaitItem()
                assertEquals("10.0", amountState.amountInput)

                viewModel.onAction(CurrencyRateUiEvent.OnGetRatesClicked)
                testScheduler.advanceUntilIdle()

                // Consumes Loading emission when loadRates starts
                assertEquals(RateStatus.Loading, awaitItem().rateStatus)

                val state = awaitItem()
                assertEquals("USD", state.selectedCurrencyCode)
                assertEquals("10.0", state.amountInput)
                assertEquals(
                    RateStatus.Success(convertedList.toCurrencyRateUiModels()),
                    state.rateStatus
                )
                cancelAndIgnoreRemainingEvents()
            }

            verifyGetCurrenciesRateListUseCaseCalled(10.0, "USD")
        }

    @Test
    fun when_rate_fetch_throws_error_then_rateStatus_is_Error() =
        runTest(rule.dispatcher.testDispatcher) {
            val errorMessage = "Network timeout"
            onGetCurrenciesRateList_thenReturnError(10.0, "USD", RuntimeException(errorMessage))

            viewModel.uiState.test {
                awaitItem() // Initial Loading from init
                testScheduler.advanceUntilIdle()
                awaitItem() // Initial empty success

                viewModel.onAction(CurrencyRateUiEvent.OnAmountChanged("10.0"))
                awaitItem() // Amount changed

                viewModel.onAction(CurrencyRateUiEvent.OnGetRatesClicked)
                testScheduler.advanceUntilIdle()

                // Consumes Loading emission when loadRates starts
                assertEquals(RateStatus.Loading, awaitItem().rateStatus)

                val state = awaitItem()
                assertEquals(RateStatus.Error(errorMessage), state.rateStatus)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun when_OnAmountChanged_then_amountInput_is_updated() =
        runTest(rule.dispatcher.testDispatcher) {
            viewModel.uiState.test {
                awaitItem() // Initial state
                viewModel.onAction(CurrencyRateUiEvent.OnAmountChanged("25.5"))

                val state = awaitItem()
                assertEquals("25.5", state.amountInput)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun when_OnCurrencySelected_then_selectedCurrency_is_updated_and_new_rates_fetched() =
        runTest(rule.dispatcher.testDispatcher) {
            val convertedList = getConvertedCurrencyList(1.0, "JPY")
            onGetCurrenciesRateList_thenReturn(1.0, "JPY", convertedList)
            viewModel.uiState.test {
                awaitItem() // Initial Loading
                testScheduler.advanceUntilIdle()
                awaitItem() // Initial Success

                viewModel.onAction(CurrencyRateUiEvent.OnCurrencySelected("JPY"))
                testScheduler.advanceUntilIdle()

                val currencyChangeState = awaitItem()
                assertEquals("JPY", currencyChangeState.selectedCurrencyCode)
                assertFalse(currencyChangeState.isCurrencyBottomSheetVisible)

                // Consumes Loading emission
                assertEquals(RateStatus.Loading, awaitItem().rateStatus)

                // Consumes Success emission
                val updatedRatesState = awaitItem()
                assertEquals("JPY", updatedRatesState.selectedCurrencyCode)
                assertEquals(
                    RateStatus.Success(convertedList.toCurrencyRateUiModels()),
                    updatedRatesState.rateStatus
                )
                cancelAndIgnoreRemainingEvents()
            }
            verifyGetCurrenciesRateListUseCaseCalled(1.0, "JPY")
        }

    @Test
    fun when_bottom_sheet_opened_and_dismissed_then_isCurrencyBottomSheetVisible_updates() =
        runTest(rule.dispatcher.testDispatcher) {
            viewModel.uiState.test {
                awaitItem() // Initial state
                viewModel.onAction(CurrencyRateUiEvent.OnOpenCurrencyBottomSheetClicked)

                val openedState = awaitItem()
                assertTrue(openedState.isCurrencyBottomSheetVisible)

                viewModel.onAction(CurrencyRateUiEvent.OnDismissCurrencyBottomSheetClicked)
                val closedState = awaitItem()
                assertFalse(closedState.isCurrencyBottomSheetVisible)

                cancelAndIgnoreRemainingEvents()
            }
        }
}