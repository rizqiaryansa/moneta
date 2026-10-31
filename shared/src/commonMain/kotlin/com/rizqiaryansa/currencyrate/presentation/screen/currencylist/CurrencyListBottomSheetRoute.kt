package com.rizqiaryansa.currencyrate.presentation.screen.currencylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rizqiaryansa.currencyrate.presentation.screen.CurrencyShimmerCompose
import com.rizqiaryansa.currencyrate.presentation.uimodel.CurrencyCountryUiModel
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyListStatus
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyListUiAction
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyListUiEffect
import com.rizqiaryansa.currencyrate.presentation.uimodel.compose.CurrencyListUiState
import com.rizqiaryansa.currencyrate.presentation.viewmodel.CurrencyListViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyListBottomSheetRoute(
    onCurrencySelected: (String) -> Unit,
    onDismiss: () -> Unit,
    viewModel: CurrencyListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val sheetState = rememberBottomSheetState(
        initialValue = SheetValue.Expanded,
        enabledValues = setOf(SheetValue.Hidden, SheetValue.Expanded)
    )

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(viewModel.uiEffect) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is CurrencyListUiEffect.NavigationBackWithCountrySelected -> {
                    coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                        onCurrencySelected(effect.currencyCode)
                    }
                }
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.Black,
        sheetState = sheetState
    ) {
        CurrencyListSheetContent(
            uiState = uiState,
            onAction = viewModel::onAction,
            onBackClick = {
                coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                    onDismiss()
                }
            }
        )
    }
}

@Composable
fun CurrencyListSheetContent(
    uiState: CurrencyListUiState,
    onAction: (CurrencyListUiAction) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    // Synchronous local state avoids input lag, double typing, and cursor jumping in Compose Multiplatform (especially iOS)
    var localSearchQuery by remember { mutableStateOf(uiState.searchQuery) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Change currency",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = localSearchQuery,
            onValueChange = { newQuery ->
                localSearchQuery = newQuery
                onAction(CurrencyListUiAction.OnSearchQueryChanged(newQuery))
            },
            placeholder = { Text("Search currency or country", color = Color.Gray) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF2B292F),
                unfocusedContainerColor = Color(0xFF2B292F),
                focusedBorderColor = Color(0xFF2B292F),
                unfocusedBorderColor = Color(0xFF2B292F),
            ),
            modifier = Modifier
                .testTag("currency_search_input")
                .fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth().weight(1f, fill = false)) {
            when (val status = uiState.currencyListStatus) {
                is CurrencyListStatus.Loading -> {
                    CurrencyShimmerCompose()
                }
                is CurrencyListStatus.Success -> {
                    if (status.data.isEmpty()) {
                        CurrencyListErrorState()
                    } else {
                        CurrencyItemList(
                            currencies = status.data,
                            onCurrencyClick = { code ->
                                onAction(CurrencyListUiAction.OnCountrySelected(code))
                            }
                        )
                    }
                }
                is CurrencyListStatus.Error -> {
                    CurrencyListErrorState()
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun CurrencyListSheetContentPreview() {
    val sampleCurrencies = persistentListOf(
        CurrencyCountryUiModel("U", "USD", "United States Dollar"),
        CurrencyCountryUiModel("E", "EUR", "Euro"),
        CurrencyCountryUiModel("J", "JPY", "Japanese Yen")
    )
    CurrencyListSheetContent(
        uiState = CurrencyListUiState(
            currencyListStatus = CurrencyListStatus.Success(sampleCurrencies)
        ),
        onAction = {},
        onBackClick = {}
    )
}