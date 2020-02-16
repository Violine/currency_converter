package com.alexander.korovin.currency.converter.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexander.korovin.currency.converter.App
import com.alexander.korovin.currency.converter.database.CurrencyDao
import com.alexander.korovin.currency.converter.network.di.RestService
import com.alexander.korovin.currency.converter.model.Currency
import com.alexander.korovin.currency.converter.model.CurrencyResponseModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository : IRepository {
    @Inject
    lateinit var restService: RestService

    @Inject
    lateinit var database : CurrencyDao

    init {
        App.instance.appComponent.inject(this)
    }

    override fun getCurrencyData(): Observable<CurrencyResponseModel> {
        return restService.getCurrencyData()

    }
}