package com.rizqiaryansa.currencyrate.presentation.uimodel.compose

sealed interface CurrencyListUiAction {
    data class OnSearchQueryChanged(val query: String): CurrencyListUiAction
    data class OnCountrySelected(val currencyCode: String): CurrencyListUiAction
}

sealed interface CurrencyListUiEffect {
    data class NavigationBackWithCountrySelected(val currencyCode: String): CurrencyListUiEffect
}