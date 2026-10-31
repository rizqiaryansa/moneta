package com.rizqiaryansa.currencyrate.core.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class CoroutineDispatchersProvider: CoroutineDispatchers {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default

    override val immediate: CoroutineDispatcher
        get() = Dispatchers.Main.immediate

    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}