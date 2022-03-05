package com.example.cryptoinfo.coinList.presentation

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
            when (resource) {
                is Resource.Success -> {
                    _state.value = CoinListUiState(coins = resource.data)
                }
                is Resource.Failure -> {
                    _state.value = CoinListUiState(error = resource.message)
                }
                Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true, error = "")
                }

            }.exhaustive
        }.launchIn(viewModelScope)
    }

}