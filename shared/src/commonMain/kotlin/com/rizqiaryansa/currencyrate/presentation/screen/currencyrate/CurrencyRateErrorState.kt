package com.rizqiaryansa.currencyrate.presentation.screen.currencyrate

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
import currencyrateappkmcp.shared.generated.resources.oops_something_went_wrong
import currencyrateappkmcp.shared.generated.resources.try_again_by_clicking_get_rates_to_load_all_currencies
import org.jetbrains.compose.resources.stringResource

@Composable
fun CurrencyConverterErrorState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.oops_something_went_wrong),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(
            text = stringResource(Res.string.try_again_by_clicking_get_rates_to_load_all_currencies),
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun CurrencyConverterEmptyStatePreview() {
    CurrencyConverterErrorState()
}