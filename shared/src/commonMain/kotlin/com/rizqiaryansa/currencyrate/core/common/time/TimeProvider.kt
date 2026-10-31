package com.rizqiaryansa.currencyrate.core.common.time

import kotlin.time.Clock

interface TimeProvider {
    fun getCurrentTimeInMillis(): Long
}

class DefaultTimeProvider: TimeProvider {

    override fun getCurrentTimeInMillis(): Long {
        return Clock.System.now().toEpochMilliseconds()
    }
}