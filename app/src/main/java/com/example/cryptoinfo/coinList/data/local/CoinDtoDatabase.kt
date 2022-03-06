package com.example.cryptoinfo.coinList.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptoinfo.coinList.data.dto.CoinDto

@Database(entities = [CoinDto::class], version = 1)
abstract class CoinDtoDatabase: RoomDatabase() {

    abstract val dao: CoinDtoDao

}