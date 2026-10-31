package com.rizqiaryansa.currencyrate.presentation.screen.currencyrate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizqiaryansa.currencyrate.presentation.uimodel.CurrencyRateUiModel
import kotlinx.collections.immutable.ImmutableList


@Composable
fun CurrencyConverterList(data: ImmutableList<CurrencyRateUiModel>) {
    Spacer(modifier = Modifier.height(8.dp))

    LazyColumn(
        Modifier
            .testTag("currency_rate_list")
            .fillMaxWidth()
    ) {
        items(data, key = { item ->
            item.currencyCode
        }) { item ->
            CurrencyConverterItem(item)
        }
    }
}

@Composable
fun CurrencyConverterItem(
    currencyRate: CurrencyRateUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFD1BAF2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currencyRate.countryLetter,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = currencyRate.currencyCode,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = currencyRate.countryName,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        Text(
            text = currencyRate.rate,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}