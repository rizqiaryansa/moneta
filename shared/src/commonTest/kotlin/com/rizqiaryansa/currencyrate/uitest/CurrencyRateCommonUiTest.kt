package com.rizqiaryansa.currencyrate.uitest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.v2.runComposeUiTest
import com.rizqiaryansa.currencyrate.presentation.screen.currencyrate.CurrencyConverterList
import com.rizqiaryansa.currencyrate.presentation.screen.currencyrate.CurrencyInputField
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class CurrencyRateCommonUiTest {

    @Test
    fun assert_currency_rate_displays_USA_currency_correctly() = runComposeUiTest {
        setContent {
            CurrencyConverterList(data = CurrencyRateData.getMockData())
        }

        onNodeWithText("United States").assertExists()
        onNodeWithText("USD").assertExists()
        onNodeWithText("1.0").assertExists()

        onNodeWithText("USD").assert(
            hasAnyAncestor(
                hasAnyChild(hasText("1.0"))
            )
        )
    }

    @Test
    fun assert_currency_rate_displays_SGD_currency_correctly() = runComposeUiTest {
        val data = CurrencyRateData.getCurrencyRateList()

        setContent {
            CurrencyConverterList(data = data)
        }

        val sgIdx = data.indexOfFirst { it.currencyCode == "SGD" }
        onNodeWithTag("currency_rate_list").performScrollToIndex(sgIdx)

        onNodeWithText("Singapore Dollar")
            .assertIsDisplayed()
            .assert(
                hasAnyAncestor(
                    hasAnyChild(hasText("SGD"))
                )
            )
    }

    @Test
    fun typing_amount_updates_textfield_correctly() = runComposeUiTest {
        setContent {
            var amountState by remember { mutableStateOf("100") }
            CurrencyInputField(
                amount = amountState,
                onAmountChange = { amountState = it },
                selectedCurrency = "USD",
                onCurrencySelectorClick = {}
            )
        }

        val amountToType = "995"
        val textField = onNodeWithTag("currency_amount")

        textField.performTextReplacement(amountToType)
        textField.assertTextContains(amountToType)
    }
}