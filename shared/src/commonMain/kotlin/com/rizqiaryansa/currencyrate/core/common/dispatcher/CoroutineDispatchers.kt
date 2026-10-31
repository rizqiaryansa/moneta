package com.rizqiaryansa.currencyrate.core.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val immediate: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}