package com.alexander.korovin.currency.converter

import android.app.Application
import com.alexander.korovin.currency.converter.network.di.DaggerRestApiComponent
import com.alexander.korovin.currency.converter.network.di.RestApiComponent
import com.alexander.korovin.currency.converter.network.di.RestApiModule

class App : Application() {
    companion object {
        lateinit var instance : App
    }

    val currencyServiceUrl : String = "https://api.exchangerate-api.com/v4/latest/"
    lateinit var restApiComponent: RestApiComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        restApiComponent = initRestApiComponent()
    }

    private fun initRestApiComponent(): RestApiComponent {
        return DaggerRestApiComponent.builder()
            .restApiModule(RestApiModule(currencyServiceUrl))
            .build()
    }
}