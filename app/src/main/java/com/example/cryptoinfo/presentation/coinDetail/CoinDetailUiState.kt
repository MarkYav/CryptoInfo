package com.example.cryptoinfo.presentation.coinDetail

import com.example.cryptoinfo.presentation.model.CoinDetail

data class CoinDetailUiState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
