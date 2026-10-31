package com.rizqiaryansa.currencyrate.presentation.uimodel.compose

import androidx.compose.runtime.Immutable
import com.rizqiaryansa.currencyrate.presentation.uimodel.CurrencyRateUiModel
import kotlinx.collections.immutable.ImmutableList

sealed interface RateStatus {
    object Loading: RateStatus
    data class Success(val data: ImmutableList<CurrencyRateUiModel>): RateStatus
    data class Error(val message: String): RateStatus
}

@Immutable
data class CurrencyRateUiState(
    val selectedCurrencyCode: String = "USD",
    val amountInput: String = "1",
    val isCurrencyBottomSheetVisible: Boolean = false,
    val rateStatus: RateStatus = RateStatus.Loading
)