package com.rizqiaryansa.currencyrate.presentation.screen.currencylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import currencyrateappkmcp.shared.generated.resources.Res
import currencyrateappkmcp.shared.generated.resources.currency_not_found
import currencyrateappkmcp.shared.generated.resources.try_searching_for_another_available_currency
import org.jetbrains.compose.resources.stringResource

@Composable
fun CurrencyListErrorState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.currency_not_found),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(
            text = stringResource(Res.string.try_searching_for_another_available_currency),
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun CurrencyListEmptyStatePreview() {
    CurrencyListErrorState()
}