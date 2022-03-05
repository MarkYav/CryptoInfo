package com.example.cryptoinfo.presentation.coinList

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoinfo.common.Resource
import com.example.cryptoinfo.common.exhaustive
import com.example.cryptoinfo.data.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private var _state by mutableStateOf(CoinListUiState())
    val state: CoinListUiState = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        coinRepository.getCoins().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _state = CoinListUiState(coins = resource.data)
                }
                is Resource.Failure -> {
                    _state = CoinListUiState(error = resource.message)
                }
                Resource.Loading -> {
                    _state = _state.copy(isLoading = true)
                }

            }.exhaustive
        }.launchIn(viewModelScope)
    }

}