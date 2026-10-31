package com.rizqiaryansa.currencyrate.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class StandardCoroutineRule(
    val dispatcher: CoroutineTestDispatchersProvider = CoroutineTestDispatchersProvider()
) {
    fun starting() {
        Dispatchers.setMain(dispatcher.testDispatcher)
    }
    fun finished() {
        Dispatchers.resetMain()
    }
}