package com.rizqiaryansa.currencyrate.presentation.screen

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

const val TWELVE_SHIMMER = 12

@Composable
fun CurrencyShimmerCompose() {
    LazyColumn {
        items(TWELVE_SHIMMER) {
            ShimmerItem()
        }
    }
}

@Composable
@Preview
fun PreviewShimmerList() {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        items(TWELVE_SHIMMER) {
            ShimmerItem()
        }
    }
}

@Composable
fun ShimmerItem() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .padding(top = 12.dp, end = 12.dp, bottom = 12.dp)
                .width(40.dp)
                .height(40.dp)
                .clip(CircleShape)
                .shimmerEffect()
        )

        Column {
            Box(
                modifier = Modifier
                    .width(128.dp)
                    .height(24.dp)
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(253.dp)
                    .height(4.dp)
                    .shimmerEffect()
            )
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition("shimmerEffect")
    val startOffsetX by transition.animateFloat(initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(), animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = "shimmerOffsetX")
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF998CAD),
                Color(0xFF998CAD),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}