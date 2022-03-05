package com.example.cryptoinfo.data.repository

import com.example.cryptoinfo.common.Resource
import com.example.cryptoinfo.data.remote.CoinPaprikaApi
import com.example.cryptoinfo.data.remote.dto.mapToCoin
import com.example.cryptoinfo.data.remote.dto.mapToCoinDetail
import com.example.cryptoinfo.presentation.model.Coin
import com.example.cryptoinfo.presentation.model.CoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val api: CoinPaprikaApi
) {

    fun getCoins(): Flow<Resource<List<Coin>>> {
        return flow {
            try {
                emit(Resource.Loading)
                val coins = api.getCoins().map { it.mapToCoin() }
                emit(Resource.Success(coins))
            } catch (e: HttpException) {
                emit(
                    Resource.Failure(
                        message = e.localizedMessage ?: "An unexpected error occurred.",
                        reason = e
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Failure(
                        message = "Couldn't reach server. Check your internet connection.",
                        reason = e
                    )
                )
            }
        }
    }

    suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetail>> {
        return flow {
            try {
                emit(Resource.Loading)
                val coin = api.getCoinById(coinId = coinId).mapToCoinDetail()
                emit(Resource.Success(coin))
            } catch (e: HttpException) {
                emit(
                    Resource.Failure(
                        message = e.localizedMessage ?: "An unexpected error occurred.",
                        reason = e
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Failure(
                        message = "Couldn't reach server. Check your internet connection.",
                        reason = e
                    )
                )
            }
        }
    }

}