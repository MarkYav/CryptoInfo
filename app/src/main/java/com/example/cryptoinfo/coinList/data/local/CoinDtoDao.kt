package com.example.cryptoinfo.coinList.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptoinfo.coinList.data.dto.CoinDto

@Dao
interface CoinDtoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDtos(coinDtos: List<CoinDto>)

    @Query("SELECT * FROM coindto")
    suspend fun getCoinDtos(): List<CoinDto>

    @Query("DELETE FROM coindto")
    suspend fun deleteCoinDtos()

}