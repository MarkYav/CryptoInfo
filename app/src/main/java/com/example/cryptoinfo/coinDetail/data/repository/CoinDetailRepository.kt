package com.example.cryptoinfo.coinDetail.data.repository

import com.example.cryptoinfo.coinDetail.data.remote.CoinDetailPaprikaApi
import com.example.cryptoinfo.coinDetail.data.remote.dto.mapToCoinDetail
import com.example.cryptoinfo.coinDetail.model.CoinDetail
import com.example.cryptoinfo.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class CoinDetailRepository @Inject constructor(
    private val api: CoinDetailPaprikaApi
) {

    fun getCoinById(coinId: String): Flow<Resource<CoinDetail>> {
        return flow {
            try {
                emit(Resource.Loading(data = null))
                val coin = api.getCoinById(coinId = coinId).mapToCoinDetail()
                emit(Resource.Success(coin))
            } catch (e: HttpException) {
                emit(
                    Resource.Failure(
                        data = null,
                        message = e.localizedMessage ?: "An unexpected error occurred.",
                        reason = e
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Failure(
                        data = null,
                        message = "Couldn't reach server. Check your internet connection.",
                        reason = e
                    )
                )
            } catch (e: NullPointerException) {
                emit(
                    Resource.Failure(
                        data = null,
                        message = "No data about this coin. Try again later.",
                        reason = e
                    )
                )
            } catch (e: Exception) {
                emit(
                    Resource.Failure(
                        data = null,
                        message = e.localizedMessage ?: "An unexpected error occurred.",
                        reason = e
                    )
                )
            }
        }
    }

}