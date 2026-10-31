package com.rizqiaryansa.currencyrate.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizqiaryansa.currencyrate.presentation.screen.currencyrate.CurrencyConvertRoute
import currencyrateappkmcp.shared.generated.resources.Res
import currencyrateappkmcp.shared.generated.resources.app_name
import currencyrateappkmcp.shared.generated.resources.currency_converter_logo
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun CurrencyRateAppWithSplash(
    innerPaddingValues: PaddingValues
) {
    var isSplashScreenVisible by remember { mutableStateOf(true) }

    LaunchedEffect(keys = arrayOf(Unit)) {
        delay(3000.milliseconds)
        isSplashScreenVisible = false
    }

    if (isSplashScreenVisible) {
        CurrencyRateSplashScreen(innerPaddingValues)
    } else {
        CurrencyConvertRoute(innerPaddingValues)
    }
}

@Composable
fun CurrencyRateSplashScreen(innerPaddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
            .background(Color.Black),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(Res.drawable.currency_converter_logo),
                    contentDescription = "Currency Converter Logo"
                )
                Text(
                    text = stringResource(Res.string.app_name),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
        FooterCurrencyRate()
    }
}

@Composable
@Preview
fun CurrencyRateSplashScreenPreview() {
    CurrencyRateSplashScreen(innerPaddingValues = PaddingValues(0.dp))
}