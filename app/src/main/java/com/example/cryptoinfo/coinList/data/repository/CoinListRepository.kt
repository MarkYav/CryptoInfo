package com.example.cryptoinfo.coinList.data.repository

import com.example.cryptoinfo.coinList.data.remote.CoinListPaprikaApi
import com.example.cryptoinfo.coinList.data.remote.dto.mapToCoin
import com.example.cryptoinfo.coinList.model.Coin
import com.example.cryptoinfo.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class CoinListRepository @Inject constructor(
    private val api: CoinListPaprikaApi
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
            } catch (e: Exception) {
                emit(
                    Resource.Failure(
                        message = e.localizedMessage ?: "An unexpected error occurred.",
                        reason = e
                    )
                )
            }
        }
    }

}