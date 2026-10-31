package com.rizqiaryansa.currencyrate.presentation.screen.currencylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizqiaryansa.currencyrate.presentation.uimodel.CurrencyCountryUiModel
import kotlinx.collections.immutable.ImmutableList

@Composable
fun CurrencyItemList(
    currencies: ImmutableList<CurrencyCountryUiModel>,
    onCurrencyClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(
            items = currencies,
            key = { currency -> currency.currencyCode }
        ) { currency ->
            CurrencyItemRow(
                currency = currency,
                onClick = { onCurrencyClick(currency.currencyCode) }
            )
        }
    }
}

@Composable
fun CurrencyItemRow(
    currency: CurrencyCountryUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFB39DDB)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currency.countryLetter,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = currency.currencyCode,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = currency.countryName,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}