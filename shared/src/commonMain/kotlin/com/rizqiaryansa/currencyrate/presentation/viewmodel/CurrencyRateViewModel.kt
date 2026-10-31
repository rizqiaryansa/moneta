package com.rizqiaryansa.currencyrate.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizqiaryansa.currencyrate.domain.usecase.GetCurrenciesRateUseCase
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyRateUiEvent
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyRateUiState
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.RateStatus
import com.rizqiaryansa.currencyrate.presentation.uimodel.toCurrencyRateUiModels
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CurrencyRateViewModel(
    private val getCurrenciesRateUseCase: Lazy<GetCurrenciesRateUseCase>,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val selectedCurrencyCode = savedStateHandle.getStateFlow(KEY_SELECTED_CURRENCY, DEFAULT_CURRENCY)
    private val amountInput = savedStateHandle.getStateFlow(KEY_AMOUNT_INPUT, DEFAULT_AMOUNT)

    private val _rateStatus = MutableStateFlow<RateStatus>(RateStatus.Loading)
    private val _isBottomSheetVisible = MutableStateFlow(false)

    val uiState: StateFlow<CurrencyRateUiState> = combine(
        selectedCurrencyCode,
        amountInput,
        _rateStatus,
        _isBottomSheetVisible
    ) { currency, amount, status, isBottomSheetVisible ->
        CurrencyRateUiState(
            currency,
            amountInput = amount,
            rateStatus = status,
            isCurrencyBottomSheetVisible = isBottomSheetVisible
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CurrencyRateUiState()
    )

    private var loadRatesJob: Job? = null

    init {
        loadRates(selectedCurrencyCode.value, amountInput.value)
    }

    fun onAction(event: CurrencyRateUiEvent) {
        when (event) {
            is CurrencyRateUiEvent.OnAmountChanged -> {
                savedStateHandle[KEY_AMOUNT_INPUT] = event.amount
            }

            is CurrencyRateUiEvent.OnCurrencySelected -> {
                savedStateHandle[KEY_SELECTED_CURRENCY] = event.currencyCode
                _isBottomSheetVisible.value = false
                loadRates(event.currencyCode, amountInput.value)
            }

            is CurrencyRateUiEvent.OnGetRatesClicked -> {
                loadRates(selectedCurrencyCode.value, amountInput.value)
            }

            is CurrencyRateUiEvent.OnOpenCurrencyBottomSheetClicked -> {
                _isBottomSheetVisible.value = true
            }

            is CurrencyRateUiEvent.OnDismissCurrencyBottomSheetClicked -> {
                _isBottomSheetVisible.value = false
            }
        }
    }

    private fun loadRates(currency: String, amountStr: String) {
        // Cancel this job to prevent race conditions if there are multiple requests
        loadRatesJob?.cancel()

        loadRatesJob = viewModelScope.launch {
            _rateStatus.value = RateStatus.Loading
            val parsedAmount = amountStr.toDoubleOrNull() ?: 1.0

            try {
                val result = getCurrenciesRateUseCase.value(
                    amount = parsedAmount,
                    selectedBaseCurrency = currency
                )
                _rateStatus.value = RateStatus.Success(result.toCurrencyRateUiModels())
            } catch (c: CancellationException) {
                throw c
            } catch (e: Throwable) {
                _rateStatus.value = RateStatus.Error(
                    e.message ?: "Failed to load exchange rates"
                )
            }
        }
    }

    companion object {
        private const val KEY_SELECTED_CURRENCY = "key_selected_currency"
        private const val KEY_AMOUNT_INPUT = "key_amount_input"
        private const val DEFAULT_AMOUNT = "1"
        private const val DEFAULT_CURRENCY = "USD"
    }
}