package com.example.cryptoinfo.coinList.data.remote

import com.example.cryptoinfo.coinList.data.dto.CoinDto
import retrofit2.http.GET

interface CoinListPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

}