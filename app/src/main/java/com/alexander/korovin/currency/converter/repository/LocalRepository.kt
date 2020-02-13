package com.alexander.korovin.currency.converter.repository

import androidx.lifecycle.LiveData
import com.alexander.korovin.currency.converter.network.model.Valute

interface LocalRepository {
    fun getCurrencyDataFromDataBase() : LiveData<Map<String, Valute>>
}