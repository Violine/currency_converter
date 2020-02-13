package com.alexander.korovin.currency.converter

import android.app.Application
import com.alexander.korovin.currency.converter.network.di.DaggerRestApiComponent
import com.alexander.korovin.currency.converter.network.di.RestApiComponent
import com.alexander.korovin.currency.converter.network.di.RestApiModule

class App : Application() {
    val currencyServiceUrl : String = "https://www.cbr-xml-daily.ru/daily_json.js"

    lateinit var restApiComponent: RestApiComponent
    lateinit var instance: Application

    override fun onCreate() {
        super.onCreate()
        instance = this
        restApiComponent = initRestApiComponent()
    }

    fun get() : Application {
        return instance
    }

    private fun initRestApiComponent(): RestApiComponent {
        return DaggerRestApiComponent.builder()
            .restApiModule(RestApiModule(currencyServiceUrl))
            .build()
    }
}