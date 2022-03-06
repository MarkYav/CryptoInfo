package com.example.cryptoinfo.coinDetail.presentation

import com.example.cryptoinfo.coinDetail.model.CoinDetail

data class CoinDetailUiState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)
