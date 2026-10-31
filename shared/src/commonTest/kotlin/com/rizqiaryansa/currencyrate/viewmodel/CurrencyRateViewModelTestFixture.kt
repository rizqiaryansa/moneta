package com.rizqiaryansa.currencyrate.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.rizqiaryansa.currencyrate.domain.model.ConvertedCurrency
import com.rizqiaryansa.currencyrate.domain.usecase.GetCurrenciesRateUseCase
import com.rizqiaryansa.currencyrate.presentation.viewmodel.CurrencyRateViewModel
import com.rizqiaryansa.currencyrate.repository.FakeCurrencyRateRepository
import com.rizqiaryansa.currencyrate.utils.StandardCoroutineRule
import com.rizqiaryansa.currencyrate.utils.getDomainCurrencies
import com.rizqiaryansa.currencyrate.utils.getDomainExchangeRates
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertTrue

open class CurrencyRateViewModelTestFixture {
    val rule = StandardCoroutineRule()

    private lateinit var fakeRepository: FakeCurrencyRateRepository
    lateinit var getCurrenciesRateUseCase: Lazy<GetCurrenciesRateUseCase>

    protected lateinit var savedStateHandle: SavedStateHandle
    protected lateinit var viewModel: CurrencyRateViewModel

    @BeforeTest
    fun setup() {
        rule.starting()
        fakeRepository = FakeCurrencyRateRepository().apply {
            currenciesToReturn = getDomainCurrencies()
        }
        getCurrenciesRateUseCase = lazyOf(GetCurrenciesRateUseCase(fakeRepository))
        savedStateHandle = SavedStateHandle()
        onGetCurrenciesRateList_thenReturn(1.0, "USD", emptyList())
        viewModel = createViewModel()
    }

    @AfterTest
    fun finish() {
        rule.finished()
    }

    protected fun createViewModel(handle: SavedStateHandle = savedStateHandle): CurrencyRateViewModel {
        return CurrencyRateViewModel(getCurrenciesRateUseCase, handle)
    }

    protected fun onGetCurrenciesRateList_thenReturn(
        amount: Double,
        selectedBaseCurrency: String,
        convertedList: List<ConvertedCurrency>
    ) {
        val rates = if (convertedList.isEmpty()) emptyList() else getDomainExchangeRates()
        fakeRepository.setExchangeRatesResponse(
            amount = amount,
            baseCurrency = selectedBaseCurrency,
            result = Result.success(rates)
        )
    }

    protected fun onGetCurrenciesRateList_thenReturnError(
        amount: Double,
        selectedBaseCurrency: String,
        throwable: Throwable
    ) {
        fakeRepository.setExchangeRatesResponse(
            amount = amount,
            baseCurrency = selectedBaseCurrency,
            result = Result.failure(throwable)
        )
    }

    protected fun verifyGetCurrenciesRateListUseCaseCalled(
        amount: Double,
        selectedBaseCurrency: String
    ) {
        assertTrue(fakeRepository.getExchangeRatesCallCount > 0)
    }
}