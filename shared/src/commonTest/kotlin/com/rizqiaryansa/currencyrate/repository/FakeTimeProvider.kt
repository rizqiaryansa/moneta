package com.rizqiaryansa.currencyrate.repository

import com.rizqiaryansa.currencyrate.core.common.time.TimeProvider

class FakeTimeProvider: TimeProvider {

    var currentTimeMillis: Long = 0L

    override fun getCurrentTimeInMillis(): Long {
        return currentTimeMillis
    }
}