package com.rizqiaryansa.currencyrate.presentation.screen.currencyrate

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rizqiaryansa.currencyrate.presentation.screen.CurrencyShimmerCompose
import com.rizqiaryansa.currencyrate.presentation.screen.currencylist.CurrencyListBottomSheetRoute
import com.rizqiaryansa.currencyrate.presentation.uimodel.CurrencyRateUiModel
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyRateUiEvent
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyRateUiState
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.RateStatus
import com.rizqiaryansa.currencyrate.presentation.viewmodel.CurrencyRateViewModel
import currencyrateappkmcp.shared.generated.resources.Res
import currencyrateappkmcp.shared.generated.resources.app_name
import currencyrateappkmcp.shared.generated.resources.conversion_rates_reference_usd
import currencyrateappkmcp.shared.generated.resources.get_rates
import currencyrateappkmcp.shared.generated.resources.rate_list
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CurrencyConvertRoute(
    innerPadding: PaddingValues,
    viewModel: CurrencyRateViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CurrencyConvertContent(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction
    )

    if (uiState.isCurrencyBottomSheetVisible) {
        CurrencyListBottomSheetRoute(
            onCurrencySelected = { selectedCode ->
                viewModel.onAction(CurrencyRateUiEvent.OnCurrencySelected(selectedCode))
            },
            onDismiss = {
                viewModel.onAction(CurrencyRateUiEvent.OnDismissCurrencyBottomSheetClicked)
            }
        )
    }
}

@Composable
fun CurrencyConvertContent(
    uiState: CurrencyRateUiState,
    innerPadding: PaddingValues,
    onAction: (CurrencyRateUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(Res.string.app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        CurrencyInputField(
            amount = uiState.amountInput,
            onAmountChange = { onAction(CurrencyRateUiEvent.OnAmountChanged(it)) },
            selectedCurrency = uiState.selectedCurrencyCode,
            onCurrencySelectorClick = { onAction(CurrencyRateUiEvent.OnOpenCurrencyBottomSheetClicked) }
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(Res.string.conversion_rates_reference_usd),
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onAction(CurrencyRateUiEvent.OnGetRatesClicked) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(text = stringResource(Res.string.get_rates), color = Color.Black, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(Res.string.rate_list),
            color = Color.White,
            fontSize = 16.sp
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ) {
            when (val status = uiState.rateStatus) {
                is RateStatus.Loading -> {
                    CurrencyShimmerCompose()
                }

                is RateStatus.Success -> {
                    CurrencyConverterList(data = status.data)
                }

                is RateStatus.Error -> {
                    CurrencyConverterErrorState()
                }
            }
        }
    }
}

@Composable
fun CurrencyInputField(
    amount: String,
    onAmountChange: (String) -> Unit,
    selectedCurrency: String,
    onCurrencySelectorClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Synchronously update avoids cursor jumping, double typing, input lag in Compose Multiplatform (iOS)
    var localAmount by remember { mutableStateOf(amount) }

    OutlinedTextField(
        value = localAmount,
        onValueChange = {
            localAmount = it
            onAmountChange(it)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            showKeyboardOnFocus = true
        ),
        textStyle = TextStyle(color = Color.White),
        trailingIcon = {
            Row(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clickable { onCurrencySelectorClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedCurrency,
                    color = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Select Currency",
                    tint = Color.White
                )
            }
        },
        modifier = modifier
            .testTag("currency_amount")
            .fillMaxWidth(),
        singleLine = true
    )
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun CurrencyConverterScreenSuccessPreview() {
    val sampleRates = persistentListOf(
        CurrencyRateUiModel("E", "EUR", "Euro", "0.92"),
        CurrencyRateUiModel("J", "JPY", "Japanese Yen", "154.20"),
        CurrencyRateUiModel("G", "GBP", "British Pound", "0.78")
    )

    CurrencyConvertContent(
        uiState = CurrencyRateUiState(
            selectedCurrencyCode = "USD",
            amountInput = "100",
            rateStatus = RateStatus.Success(sampleRates)
        ),
        innerPadding = PaddingValues(0.dp),
        onAction = {}
    )
}
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun CurrencyConverterScreenLoadingPreview() {
    CurrencyConvertContent(
        uiState = CurrencyRateUiState(rateStatus = RateStatus.Loading),
        innerPadding = PaddingValues(0.dp),
        onAction = {}
    )
}