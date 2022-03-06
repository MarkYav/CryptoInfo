package com.example.cryptoinfo.coinList.data.repository

import com.example.cryptoinfo.coinList.data.dto.mapToCoin
import com.example.cryptoinfo.coinList.data.local.CoinDtoDao
import com.example.cryptoinfo.coinList.data.remote.CoinListPaprikaApi
import com.example.cryptoinfo.coinList.model.Coin
import com.example.cryptoinfo.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinListRepository @Inject constructor(
    private val api: CoinListPaprikaApi,
    private val dao: CoinDtoDao
) {

    fun getCoins(): Flow<Resource<List<Coin>>> {
        return flow {
            emit(Resource.Loading(data = null))

            val cachedCoinList = dao.getCoinDtos().map { it.mapToCoin() }
            emit(Resource.Loading(data = cachedCoinList))

            try {
                val fetchedCoinDtoList = api.getCoins()
                val fetchedCoinList = fetchedCoinDtoList.map { it.mapToCoin() }

                dao.deleteCoinDtos()
                dao.insertCoinDtos(fetchedCoinDtoList)

                emit(Resource.Success(fetchedCoinList))
            } catch (e: HttpException) {
                emit(
                    Resource.Failure(
                        data = cachedCoinList,
                        message = e.localizedMessage ?: "An unexpected error occurred.",
                        reason = e
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Failure(
                        data = cachedCoinList,
                        message = "Couldn't reach server. Check your internet connection.",
                        reason = e
                    )
                )
            } catch (e: Exception) {
                emit(
                    Resource.Failure(
                        data = cachedCoinList,
                        message = e.localizedMessage ?: "An unexpected error occurred.",
                        reason = e
                    )
                )
            }
        }
    }

}