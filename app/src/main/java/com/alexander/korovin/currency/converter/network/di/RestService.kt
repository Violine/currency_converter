package com.alexander.korovin.currency.converter.network.di

import com.alexander.korovin.currency.converter.network.model.CurrencyResponseModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface RestService {
    @GET ("USD")
    fun getCurrencyData() : Call<CurrencyResponseModel>
}