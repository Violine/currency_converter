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
import retrofit2.Callback
import javax.inject.Inject

class Repository : NetworkRepository, LocalRepository {
    @Inject
    lateinit var restService: RestService

    init {
        App.instance.restApiComponent.inject(this)
    }

    override fun fetchCurrencyData(): LiveData<ArrayList<Currency>> {
       // restService.getCurrencyData().enqueue()
        return MutableLiveData<ArrayList<Currency>>()
    }

    override fun getCurrencyDataFromDataBase(): LiveData<ArrayList<Currency>> {
        return MutableLiveData<ArrayList<Currency>>()
    }

    fun getCurrencyList(currencyMap: Map<String, Double>): ArrayList<Currency> {
        var currencyList = ArrayList<Currency>()
        for ((k, v) in currencyMap) {
            currencyList.add(Currency(k, v))
        }
        return currencyList
    }
}