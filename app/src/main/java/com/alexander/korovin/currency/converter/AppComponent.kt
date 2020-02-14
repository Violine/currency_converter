package com.alexander.korovin.currency.converter

import com.alexander.korovin.currency.converter.database.di.DataBaseComponent
import com.alexander.korovin.currency.converter.database.di.RoomModule
import com.alexander.korovin.currency.converter.network.di.RestApiComponent
import com.alexander.korovin.currency.converter.network.di.RestApiModule
import com.alexander.korovin.currency.converter.repository.Repository
import com.alexander.korovin.currency.converter.viewmodels.MainViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(viewModelFactory: MainViewModelFactory)
}