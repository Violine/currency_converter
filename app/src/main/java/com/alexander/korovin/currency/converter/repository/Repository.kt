package com.alexander.korovin.currency.converter.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexander.korovin.currency.converter.App
import com.alexander.korovin.currency.converter.database.CurrencyDao
import com.alexander.korovin.currency.converter.network.di.RestService
import com.alexander.korovin.currency.converter.model.Currency
import com.alexander.korovin.currency.converter.model.CurrencyResponseModel
import io.reactivex.Observable
import javax.inject.Inject

class Repository : NetworkRepository, LocalRepository {
    @Inject
    lateinit var restService: RestService

    @Inject
    lateinit var database : CurrencyDao

    init {
        App.instance.restApiComponent.inject(this)
    }

    override fun fetchCurrencyData(): Observable<CurrencyResponseModel> {
        return restService.getCurrencyData()
    }

    override fun getCurrencyDataFromDataBase(): LiveData<ArrayList<Currency>> {
        return MutableLiveData<ArrayList<Currency>>()
    }
}