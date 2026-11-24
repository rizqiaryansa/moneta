package com.rizqiaryansa.currencyrate.core.common.time

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlin.time.Clock

interface TimeProvider {
    fun getCurrentTimeInMillis(): Long
}

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
@Inject
class DefaultTimeProvider: TimeProvider {

    override fun getCurrentTimeInMillis(): Long {
        return Clock.System.now().toEpochMilliseconds()
    }
}