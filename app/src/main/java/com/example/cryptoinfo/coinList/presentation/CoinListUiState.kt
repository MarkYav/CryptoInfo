package com.example.cryptoinfo.coinList.presentation

import com.example.cryptoinfo.coinList.model.Coin

data class CoinListUiState (
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)