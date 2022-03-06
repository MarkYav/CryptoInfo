package com.example.cryptoinfo.di

import android.app.Application
import androidx.room.Room
import com.example.cryptoinfo.coinDetail.data.remote.CoinDetailPaprikaApi
import com.example.cryptoinfo.coinList.data.local.CoinDtoDao
import com.example.cryptoinfo.coinList.data.local.CoinDtoDatabase
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

    @Provides
    @Singleton
    fun provideCoinDtoDao(
        coinDtoDatabase: CoinDtoDatabase
    ): CoinDtoDao {
        return coinDtoDatabase.dao
    }

    @Provides
    @Singleton
    fun provideCoinDtoDatabase(
        app: Application
    ): CoinDtoDatabase {
        return Room.databaseBuilder(
            app,
            CoinDtoDatabase::class.java,
            "coin_dto_database"
        ).build()
    }

}