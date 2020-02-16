package com.alexander.korovin.currency.converter.di

import com.alexander.korovin.currency.converter.database.di.RoomModule
import com.alexander.korovin.currency.converter.network.di.RestApiModule
import com.alexander.korovin.currency.converter.repository.Repository
import com.alexander.korovin.currency.converter.viewmodels.MainViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class, RestApiModule::class])
interface AppComponent {
    fun inject(viewModelFactory: MainViewModelFactory)
    fun inject(repository: Repository)
}