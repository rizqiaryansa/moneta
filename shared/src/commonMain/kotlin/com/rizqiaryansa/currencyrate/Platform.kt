package com.rizqiaryansa.currencyrate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform