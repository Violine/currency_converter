package com.alexander.korovin.currency.converter.network.di

import androidx.room.Room
import com.alexander.korovin.currency.converter.App
import com.alexander.korovin.currency.converter.Constanst
import com.alexander.korovin.currency.converter.database.CurrencyDao
import com.alexander.korovin.currency.converter.database.CurrencyDataBase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RestApiModule(val baseUrl: String) {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRestService(client: OkHttpClient): RestService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build().create(RestService::class.java)
    }

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