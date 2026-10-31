package com.rizqiaryansa.currencyrate.presentation.uimodel.compose

sealed interface CurrencyRateUiEvent {
    data class OnAmountChanged(val amount: String): CurrencyRateUiEvent
    data class OnCurrencySelected(val currencyCode: String): CurrencyRateUiEvent
    object OnGetRatesClicked: CurrencyRateUiEvent
    object OnOpenCurrencyBottomSheetClicked: CurrencyRateUiEvent
    object OnDismissCurrencyBottomSheetClicked: CurrencyRateUiEvent
}