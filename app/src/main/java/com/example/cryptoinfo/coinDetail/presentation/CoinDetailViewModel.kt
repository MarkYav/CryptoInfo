package com.example.cryptoinfo.coinDetail.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoinfo.coinDetail.data.repository.CoinDetailRepository
import com.example.cryptoinfo.common.Constants
import com.example.cryptoinfo.common.Resource
import com.example.cryptoinfo.common.exhaustive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinDetailRepository: CoinDetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _state = mutableStateOf(CoinDetailUiState())
    val state: State<CoinDetailUiState> = _state

    private var coinId: String? = null

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            this.coinId = coinId
            getCoin(coinId = coinId)
        }
    }

    private fun getCoin(coinId: String) {
        coinDetailRepository.getCoinById(coinId = coinId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _state.value = CoinDetailUiState(coinDetail = resource.data)
                }
                is Resource.Failure -> {
                    _state.value = CoinDetailUiState(error = resource.message)
                }
                Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true, error = "")
                }

            }.exhaustive
        }.launchIn(viewModelScope)
    }

    fun refresh() {
        coinId?.let {
            getCoin(it)
        }
    }

}