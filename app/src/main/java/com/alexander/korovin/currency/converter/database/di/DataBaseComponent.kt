package com.alexander.korovin.currency.converter.database.di

import com.alexander.korovin.currency.converter.AppModule
import com.alexander.korovin.currency.converter.repository.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])
interface DataBaseComponent {
    fun inject(repository: Repository)
}