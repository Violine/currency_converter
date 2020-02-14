package com.alexander.korovin.currency.converter

import android.app.Application
import com.alexander.korovin.currency.converter.database.di.DataBaseComponent
import com.alexander.korovin.currency.converter.network.di.RestApiComponent
import com.alexander.korovin.currency.converter.network.di.RestApiModule

class App : Application() {
    companion object {
        lateinit var instance : App
    }

    lateinit var appComponent: AppComponent
    lateinit var restApiComponent: RestApiComponent
    lateinit var dataBaseComponent: DataBaseComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = initAppComponent()
        restApiComponent = initRestApiComponent()
        dataBaseComponent = initDataBaseComponent()
    }

    private fun initRestApiComponent(): RestApiComponent {
        return DaggerRestApiComponent.builder()
            .resApiModule(RestApiModule(Constanst.CURRENCY_RATE_BASE_URL))
            .build()
    }

    private fun initDataBaseComponent() : DataBaseComponent {
        return DaggerDataBaseComponent().create()
    }

    private fun initAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}