package com.alexander.korovin.currency.converter.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexander.korovin.currency.converter.App
import com.alexander.korovin.currency.converter.network.di.RestService
import com.alexander.korovin.currency.converter.network.model.Currency
import com.alexander.korovin.currency.converter.network.model.CurrencyResponseModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository : NetworkRepository, LocalRepository {
    @Inject
    lateinit var restService: RestService

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