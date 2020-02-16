package com.alexander.korovin.currency.converter.repository

import com.alexander.korovin.currency.converter.model.CurrencyResponseModel
import io.reactivex.Observable
import java.util.*

interface IRepository {
    fun getCurrencyData() : Observable<CurrencyResponseModel>
}