package com.alexander.korovin.currency.converter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexander.korovin.currency.converter.utils.Constanst
import com.alexander.korovin.currency.converter.model.Currency

@Database(entities = arrayOf(Currency::class), version = Constanst.DATABASE_VERSION)
abstract class CurrencyDataBase : RoomDatabase() {
    abstract fun getCurrencyDao() : CurrencyDao
}