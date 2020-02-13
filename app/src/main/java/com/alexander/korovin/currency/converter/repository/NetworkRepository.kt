package com.alexander.korovin.currency.converter.repository

import androidx.lifecycle.LiveData
import com.alexander.korovin.currency.converter.network.model.Valute

interface NetworkRepository {
    fun fetchCurrencyData () : LiveData< Map<String, Valute>>
}