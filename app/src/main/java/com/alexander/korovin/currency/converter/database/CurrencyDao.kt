package com.alexander.korovin.currency.converter.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexander.korovin.currency.converter.model.Currency
import com.google.android.material.circularreveal.CircularRevealHelper

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency_items")
    fun getAll(): LiveData<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Currency>)
}