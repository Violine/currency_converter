package com.alexander.korovin.currency.converter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexander.korovin.currency.converter.network.model.Currency
import com.alexander.korovin.currency.converter.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel (var repository: Repository) : ViewModel() {

    var currencyList: LiveData<ArrayList<Currency>> = MutableLiveData()

    init {
        currencyList = repository.fetchCurrencyData()
    }
}
