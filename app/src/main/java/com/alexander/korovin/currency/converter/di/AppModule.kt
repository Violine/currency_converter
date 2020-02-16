package com.alexander.korovin.currency.converter.di

import com.alexander.korovin.currency.converter.App
import com.alexander.korovin.currency.converter.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var application: App) {

    @Singleton
    @Provides
    fun provideApp(): App {
        return application
    }

    @Singleton
    @Provides
    fun provideRepository(): Repository {
        return Repository()
    }
}