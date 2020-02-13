package com.alexander.korovin.currency.converter.repository

import androidx.lifecycle.LiveData
import com.alexander.korovin.currency.converter.network.model.Currency

interface LocalRepository {
    fun getCurrencyDataFromDataBase() : LiveData<ArrayList<Currency>>
}