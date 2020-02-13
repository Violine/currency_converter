package com.alexander.korovin.currency.converter.network.di

import com.alexander.korovin.currency.converter.repository.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RestApiModule::class])
interface RestApiComponent {
    fun inject(repo: Repository)
}