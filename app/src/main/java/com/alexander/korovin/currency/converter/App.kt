package com.alexander.korovin.currency.converter

import android.app.Application
import com.alexander.korovin.currency.converter.di.AppComponent
import com.alexander.korovin.currency.converter.di.AppModule
import com.alexander.korovin.currency.converter.di.DaggerAppComponent
import com.alexander.korovin.currency.converter.network.di.RestApiModule
import com.alexander.korovin.currency.converter.utils.Constanst

class App : Application() {
    companion object {
        lateinit var instance : App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = initAppComponent()
    }

    private fun initAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .restApiModule(RestApiModule(Constanst.CURRENCY_RATE_BASE_URL))
            .build()
    }
}