package com.example.cryptoinfo.presentation.coinList

import com.example.cryptoinfo.presentation.model.Coin

data class CoinListUiState (
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)