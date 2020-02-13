package com.alexander.korovin.currency.converter.network.di

import com.alexander.korovin.currency.converter.network.model.CurrencyDataModel
import io.reactivex.Observable
import retrofit2.http.GET

interface RestService {
    @GET ("")
    fun getCurrencyData() : Observable<CurrencyDataModel>
}