package com.alexander.korovin.currency.converter.database.di

import androidx.room.Room
import com.alexander.korovin.currency.converter.App
import com.alexander.korovin.currency.converter.utils.Constanst
import com.alexander.korovin.currency.converter.database.CurrencyDao
import com.alexander.korovin.currency.converter.database.CurrencyDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule () {
    @Singleton
    @Provides
    fun provideDataBase(application : App): CurrencyDataBase {
        return Room.databaseBuilder(application, CurrencyDataBase::class.java, Constanst.DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideCurrencyDao (dataBase: CurrencyDataBase) : CurrencyDao {
        return dataBase.getCurrencyDao()
    }
}