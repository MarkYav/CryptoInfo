package com.example.cryptoinfo.coinDetail.data.remote

import com.example.cryptoinfo.coinDetail.data.remote.dto.CoinDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinDetailPaprikaApi {

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}