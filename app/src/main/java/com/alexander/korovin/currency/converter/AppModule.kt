package com.alexander.korovin.currency.converter

import dagger.Module
import dagger.Provides

@Module
class AppModule (var application : App) {

    @Provides
    fun provideApp() : App {
        return application
    }
}