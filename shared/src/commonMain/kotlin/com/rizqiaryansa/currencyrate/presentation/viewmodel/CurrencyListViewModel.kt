package com.rizqiaryansa.currencyrate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizqiaryansa.currencyrate.domain.usecase.GetCurrenciesListUseCase
import com.rizqiaryansa.currencyrate.domain.usecase.SearchCurrenciesUseCase
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyListStatus
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyListUiAction
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyListUiEffect
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyListUiState
import com.rizqiaryansa.currencyrate.presentation.uimodel.toCurrencyCountryUiModels
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class)
class CurrencyListViewModel(
    private val getCurrenciesListUseCase: Lazy<GetCurrenciesListUseCase>,
    private val searchCurrenciesUseCase: Lazy<SearchCurrenciesUseCase>
): ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _currencyListStatus =
        MutableStateFlow<CurrencyListStatus>(CurrencyListStatus.Loading)

    // SharedFlow with extraBufferCapacity to prevent dropped events
    private val _uiEffect = MutableSharedFlow<CurrencyListUiEffect>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val uiEffect: SharedFlow<CurrencyListUiEffect>
        get() = _uiEffect.asSharedFlow()

    val uiState: StateFlow<CurrencyListUiState> = combine(
        _searchQuery,
        _currencyListStatus
    ) { searchQuery, status ->
        CurrencyListUiState(searchQuery, status)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CurrencyListUiState()
    )

    init {
        observeSearchQuery()
    }

    fun onAction(event: CurrencyListUiAction) {
        when (event) {
            is CurrencyListUiAction.OnSearchQueryChanged -> {
                _searchQuery.value = event.query
            }

            is CurrencyListUiAction.OnCountrySelected -> {
                viewModelScope.launch {
                    _uiEffect.emit(
                        CurrencyListUiEffect.NavigationBackWithCountrySelected(event.currencyCode)
                    )
                }
            }
        }
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery
                .debounce(SEARCH_DEBOUNCE_MILLIS.milliseconds)
                .distinctUntilChanged()
                .collectLatest {
                    _currencyListStatus.value = CurrencyListStatus.Loading
                    try {
                        val result = if (it.isNotBlank()) {
                            searchCurrenciesUseCase.value(it)
                        } else {
                            getCurrenciesListUseCase.value()
                        }
                        _currencyListStatus.value = CurrencyListStatus.Success(result.toCurrencyCountryUiModels())
                    } catch (c: CancellationException) {
                        throw c
                    } catch (e: Throwable) {
                        _currencyListStatus.value = CurrencyListStatus.Error(
                            e.message ?: "Failed to find currencies"
                        )
                    }
                }
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE_MILLIS = 300L
    }

}