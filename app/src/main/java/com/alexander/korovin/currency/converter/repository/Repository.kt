package com.alexander.korovin.currency.converter.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexander.korovin.currency.converter.network.model.Valute

class Repository : NetworkRepository, LocalRepository {

    init{

    }

    override fun fetchCurrencyData(): LiveData<Map<String, Valute>> {
       return MutableLiveData<Map<String, Valute>>()
    }

    override fun getCurrencyDataFromDataBase(): LiveData<Map<String, Valute>> {
        return MutableLiveData<Map<String, Valute>>()
    }
}