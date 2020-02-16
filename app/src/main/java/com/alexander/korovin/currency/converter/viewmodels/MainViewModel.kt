package com.alexander.korovin.currency.converter.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexander.korovin.currency.converter.model.Currency
import com.alexander.korovin.currency.converter.model.CurrencyResponseModel
import com.alexander.korovin.currency.converter.repository.Repository
import com.alexander.korovin.currency.converter.utils.ErrorCode
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(var repository: Repository) : ViewModel() {

    var toCurrency : Currency? = null
    var fromCurrency : Currency? = null
    var quantity: Double = 0.0
    var currencyList : List<Currency> = emptyList()

    var state: MutableLiveData<CurrencyListState> = MutableLiveData()

    init {
        state.value = LoadingState(emptyList())
    }


    fun restoreCurrencyList() {
        state.value = ReadyState (obtainCurrentData())
    }

    private fun obtainCurrentData() = state.value?.currencyList ?: emptyList()

    @SuppressLint("CheckResult")
    fun getCurrencyList() {
        if (currencyList.isEmpty()) {
            repository.getCurrencyData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { onCurrencyListReady(it) },
                    onError = { onNetworkError() }
                )
        } else {
            postCurrencyList(currencyList)
        }
    }

    @SuppressLint("CheckResult")
    private fun onNetworkError() {
        Observable.fromCallable {
            repository.database.getAll()
        }.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    state.value = ErrorState(ErrorCode.NO_CURRENCY_DATA_ERROR, emptyList())
                },
                onNext = {
                    if (it.isNotEmpty()) {
                        postCurrencyList (it)
                    } else {
                        state.value = ErrorState(ErrorCode.NO_CURRENCY_DATA_ERROR, emptyList())
                    }

                }
            )
    }

    private fun onCurrencyListReady(response: CurrencyResponseModel) {
        var currencyList: List<Currency> = getCurrencyList(response.rates)
        if (currencyList.isNotEmpty()) {
            Observable.fromCallable {
                repository.database.insert(currencyList)
            }.subscribeOn(Schedulers.newThread()).subscribe()
            postCurrencyList(currencyList)
        } else {
            onNetworkError()
        }
    }

    private fun postCurrencyList(list: List<Currency>) {
        currencyList = list
        state.value = ReadyState(list)
        if (fromCurrency == null) fromCurrency = list.get(0)
        if (toCurrency == null) toCurrency = list.get(0)
    }

    private fun getCurrencyList(currencyMap: Map<String, Double>): ArrayList<Currency> {
        var currencyList = ArrayList<Currency>()
        for ((k, v) in currencyMap) {
            currencyList.add(Currency(k, v))
        }
        return currencyList
    }

    fun convertCurrency() {
        if (toCurrency != null && fromCurrency != null) {
            var result: Double = (toCurrency!!.value/ fromCurrency!!.value) * quantity
            var roundResult: Double = BigDecimal(result).setScale(2, RoundingMode.HALF_UP).toDouble()
            state.value = ConvertResultState(roundResult, emptyList())
        } else {
            state.value = ErrorState(ErrorCode.EMPTY_FIELD_ERROR, emptyList())
        }
    }
}
