package com.example.cryptoinfo.coinList.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoinfo.coinList.data.repository.CoinListRepository
import com.example.cryptoinfo.common.Resource
import com.example.cryptoinfo.common.exhaustive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinListRepository: CoinListRepository
) : ViewModel() {

    private var _state = mutableStateOf(CoinListUiState())
    val state: State<CoinListUiState> = _state

    init {
        getCoins()
    }

    fun getCoins() {
        coinListRepository.getCoins().onEach { resource ->
            Log.i("TAG", "coins before: ${_state.value.coins.size}")
            when (resource) {
                is Resource.Success -> {
                    _state.value = CoinListUiState(coins = resource.data)
                }
                is Resource.Failure -> {
                    _state.value = CoinListUiState(
                        coins = resource.data ?: _state.value.coins,
                        error = resource.message
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinListUiState(
                        isLoading = true,
                        coins = resource.data ?: _state.value.coins,
                        error = ""
                    )
                }
            }.exhaustive
            Log.i("TAG", "coins after: ${_state.value.coins.size}")
        }.launchIn(viewModelScope)
    }

}