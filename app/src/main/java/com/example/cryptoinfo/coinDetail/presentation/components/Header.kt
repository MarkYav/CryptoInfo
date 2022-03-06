package com.example.cryptoinfo.coinDetail.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cryptoinfo.coinDetail.model.CoinDetail
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun Header(coinDetail: CoinDetail) {
    Title(coinDetail = coinDetail)
    Spacer(modifier = Modifier.height(15.dp))
    Description(description = coinDetail.description)
    Spacer(modifier = Modifier.height(15.dp))
    Tags(tags = coinDetail.tags)
}

@Composable
private fun Title(coinDetail: CoinDetail) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${coinDetail.rank}. ${coinDetail.name} (${coinDetail.symbol})",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.weight(8f)
        )
        Text(
            text = if (coinDetail.isActive) "active" else "inactive",
            color = if (coinDetail.isActive) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(2f)
        )
    }
}

@Composable
private fun Description(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.body2
    )
}

@Composable
private fun Tags(tags: List<String>) {
    Text(
        text = "Tags",
        style = MaterialTheme.typography.h3
    )
    Spacer(modifier = Modifier.height(15.dp))
    FlowRow(
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        tags.forEach { tag ->
            CoinTag(tag = tag)
        }
    }
}