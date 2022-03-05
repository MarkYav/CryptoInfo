package com.example.cryptoinfo.di

import com.example.cryptoinfo.coinDetail.data.remote.CoinDetailPaprikaApi
import com.example.cryptoinfo.coinList.data.remote.CoinListPaprikaApi
import com.example.cryptoinfo.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinDetailPaprikaApi(): CoinDetailPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinDetailPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinListPaprikaApi(): CoinListPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinListPaprikaApi::class.java)
    }

}