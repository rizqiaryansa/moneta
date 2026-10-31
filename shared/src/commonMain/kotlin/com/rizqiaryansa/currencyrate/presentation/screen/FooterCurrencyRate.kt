package com.rizqiaryansa.currencyrate.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import currencyrateappkmcp.shared.generated.resources.Res
import currencyrateappkmcp.shared.generated.resources.currency_converter_footer
import org.jetbrains.compose.resources.stringResource

@Composable
fun FooterCurrencyRate() {
    Text(
        text = stringResource(Res.string.currency_converter_footer),
        fontSize = 18.sp,
        color = Color(0xFFFFFFFF),
        modifier = Modifier
            .padding(16.dp)    )
}