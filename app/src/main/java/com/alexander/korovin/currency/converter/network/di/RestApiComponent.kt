package com.alexander.korovin.currency.converter.network.di

import com.alexander.korovin.currency.converter.AppModule
import com.alexander.korovin.currency.converter.repository.Repository
import com.alexander.korovin.currency.converter.viewmodels.MainViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RestApiModule::class])
interface RestApiComponent {
    fun inject(repo: Repository)
    fun inject(viewModelFactory: MainViewModelFactory)
}