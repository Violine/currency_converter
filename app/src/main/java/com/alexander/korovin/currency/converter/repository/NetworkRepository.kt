package com.alexander.korovin.currency.converter.repository

import com.alexander.korovin.currency.converter.model.CurrencyResponseModel
import io.reactivex.Observable

interface NetworkRepository {
    fun fetchCurrencyData () : Observable<CurrencyResponseModel>
}