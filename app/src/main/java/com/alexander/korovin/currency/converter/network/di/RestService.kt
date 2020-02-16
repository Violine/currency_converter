package com.alexander.korovin.currency.converter.network.di

import com.alexander.korovin.currency.converter.utils.Constanst
import com.alexander.korovin.currency.converter.model.CurrencyResponseModel
import io.reactivex.Observable
import retrofit2.http.GET

interface RestService {
    @GET (Constanst.BASE_CURRENCY_REQUEST)
    fun getCurrencyData() : Observable<CurrencyResponseModel>
}