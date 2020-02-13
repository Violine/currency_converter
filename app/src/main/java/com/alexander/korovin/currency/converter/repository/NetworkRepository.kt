package com.alexander.korovin.currency.converter.repository

import androidx.lifecycle.LiveData
import com.alexander.korovin.currency.converter.network.model.Currency
import com.alexander.korovin.currency.converter.network.model.CurrencyResponseModel
import io.reactivex.Observable

interface NetworkRepository {
    fun fetchCurrencyData () : Observable<CurrencyResponseModel>
}