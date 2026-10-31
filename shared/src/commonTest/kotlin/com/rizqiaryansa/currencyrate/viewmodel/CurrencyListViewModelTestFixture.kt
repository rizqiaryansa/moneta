package com.rizqiaryansa.currencyrate.viewmodel

import com.rizqiaryansa.currencyrate.domain.model.Currency
import com.rizqiaryansa.currencyrate.domain.usecase.GetCurrenciesListUseCase
import com.rizqiaryansa.currencyrate.domain.usecase.SearchCurrenciesUseCase
import com.rizqiaryansa.currencyrate.presentation.viewmodel.CurrencyListViewModel
import com.rizqiaryansa.currencyrate.repository.FakeCurrencyRateRepository
import com.rizqiaryansa.currencyrate.utils.StandardCoroutineRule
import com.rizqiaryansa.currencyrate.utils.getDomainCurrencies
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertTrue

open class CurrencyListViewModelTestFixture {

    val rule = StandardCoroutineRule()

    private lateinit var fakeRepository: FakeCurrencyRateRepository
    lateinit var getCurrenciesListUseCase: Lazy<GetCurrenciesListUseCase>
    lateinit var searchCurrenciesUseCase: Lazy<SearchCurrenciesUseCase>

    protected lateinit var viewModel: CurrencyListViewModel

    @BeforeTest
    fun setup() {
        rule.starting()
        fakeRepository = FakeCurrencyRateRepository()
        getCurrenciesListUseCase = lazyOf(GetCurrenciesListUseCase(fakeRepository))
        searchCurrenciesUseCase = lazyOf(SearchCurrenciesUseCase(fakeRepository))
        onGetCurrenciesList_thenReturn(emptyList())
        viewModel = createViewModel()
    }

    @AfterTest
    fun finish() {
        rule.finished()
    }

    protected fun createViewModel(): CurrencyListViewModel {
        return CurrencyListViewModel(
            getCurrenciesListUseCase = getCurrenciesListUseCase,
            searchCurrenciesUseCase = searchCurrenciesUseCase,
        )
    }

    protected fun onGetCurrenciesList_thenReturn(currencyList: List<Currency>) {
        fakeRepository.currenciesToReturn = currencyList
        fakeRepository.currenciesError = null
    }

    protected fun onGetCurrenciesList_thenReturnError(throwable: Throwable) {
        fakeRepository.currenciesError = throwable
    }

    protected fun verifyGetCurrenciesListUseCaseCalled() {
        assertTrue(fakeRepository.getCurrenciesCallCount > 0)
    }

    protected fun onSearchCurrencies_thenReturn(keyword: String, currencyList: List<Currency>) {
        fakeRepository.currenciesToReturn = currencyList
        fakeRepository.currenciesError = null
    }

    protected fun onSearchCurrencies_thenReturnError(keyword: String, throwable: Throwable) {
        fakeRepository.currenciesError = throwable
    }

    protected fun verifySearchCurrenciesUseCaseCalled(keyword: String) {
        assertTrue(fakeRepository.getCurrenciesCallCount > 0)
    }
}