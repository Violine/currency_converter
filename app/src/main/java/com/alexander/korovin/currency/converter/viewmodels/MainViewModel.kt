package com.alexander.korovin.currency.converter.viewmodels

import androidx.lifecycle.ViewModel
import com.alexander.korovin.currency.converter.network.model.Currency
import com.alexander.korovin.currency.converter.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel (var repository: Repository) : ViewModel() {

    init {
        repository.fetchCurrencyData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    var list = getCurrencyList(it.rates)
                    list.size
                },
                onError = { it.printStackTrace() },
                onComplete = { println("Done!") }
            )
    }

    fun getCurrencyList(currencyMap: Map<String, Double>): ArrayList<Currency> {
        var currencyList = ArrayList<Currency>()
        for ((k, v) in currencyMap) {
            currencyList.add(Currency(k, v))
        }
        return currencyList
    }
}
