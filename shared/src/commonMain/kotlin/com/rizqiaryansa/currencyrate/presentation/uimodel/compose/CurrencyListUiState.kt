package com.rizqiaryansa.currencyrate.presentation.uimodel.compose

import androidx.compose.runtime.Immutable
import com.rizqiaryansa.currencyrate.presentation.uimodel.CurrencyCountryUiModel
import kotlinx.collections.immutable.ImmutableList

sealed interface CurrencyListStatus {
    object Loading: CurrencyListStatus
    data class Success(val data: ImmutableList<CurrencyCountryUiModel>): CurrencyListStatus
    data class Error(val message: String): CurrencyListStatus
}

@Immutable
data class CurrencyListUiState(
    val searchQuery: String = "",
    val currencyListStatus: CurrencyListStatus = CurrencyListStatus.Loading
)